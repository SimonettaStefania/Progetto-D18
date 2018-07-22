package services;

import menu.*;
import restaurant.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class DatabaseManager {
    private Catalogue catalogue;
    private ArrayList<Reservation> reservations;

    public DatabaseManager (Restaurant restaurant) {
        this.catalogue = restaurant.getDishesCatalogue();
        this.reservations = restaurant.getReservationList();
    }

    public synchronized void readDatabase() {
        DbReader dbr = DbReader.getDbReaderInstance();

        executeQuery(Query.SELECT_ALL_DISHES);
        for (MenuElement elem : dbr.getDishesList())
            catalogue.addElement(elem);

        executeQuery(Query.SELECT_ALL_ALLERGENS);
        for (Allergen item : dbr.getAllergensList())
            catalogue.addAllergen(item);

        catalogue.getDishes().sort(MenuElement.priceComparator);
        catalogue.getDishes().sort(MenuElement.typeComparator);

        populateReservations();
    }

    private synchronized void populateReservations(){
        DbReader dbr = DbReader.getDbReaderInstance();
        executeQuery(Query.SELECT_RESERVATION);
        reservations.addAll(dbr.getReservationsList());

        for (Reservation reservation : reservations) {
            String addToQuery = "WHERE RES_CODE ='" + reservation.getReservationCode() + "' ORDER BY MENU_CODE";
            executeQuery(Query.editQuery(Query.SELECT_MENU, addToQuery));

            reservation.getCreatedMenu().addAll(dbr.getMenuList());
            readMenuDishes(reservation);
        }
    }

    private synchronized void readMenuDishes (Reservation reservation) {
        DbReader dbr = DbReader.getDbReaderInstance();
        int arraySize = reservation.getCreatedMenu().size();
        String addToQuery;

        for (int menuIndex = 0; menuIndex < arraySize; menuIndex++) {
            Menu currentMenu = reservation.getCreatedMenu().get(menuIndex);

            addToQuery = "WHERE (MENU_CODE='" + menuIndex + "' AND RES_CODE='" + reservation.getReservationCode() + "')";
            executeQuery(Query.editQuery(Query.SELECT_DISH_IN_MENU,addToQuery));
            for(String code: dbr.getMenuDishesCodeList()){
                currentMenu.getMenuElementsList().add(catalogue.getElementByCode(code));
            }
        }
    }

    public synchronized void insertReservation (Reservation reservation) {
        String id = generateReservationId();
        reservation.setReservationCode(id);

        reservations.add(reservation);
        String addToQuery = String.format("('%s',%d,%s,'%s','%s','%s',NULL)", reservation.getReservationCode(),
                reservation.getnGuests(), reservation.getReservationCost(), new Date(reservation.getEventDate().getTime()),
                reservation.getCustomerNameSurname(), reservation.getCustomerMail());
        executeQuery(Query.editQuery(Query.INSERT_RESERVATION,addToQuery));

        insertMenus(reservation);
    }

    private synchronized void insertMenus (Reservation reservation) {
        int arraySize = reservation.getCreatedMenu().size();
        Locale.setDefault(Locale.US);
        String addToQuery;

        for (int menuIndex=0; menuIndex<arraySize; menuIndex++){
            Menu currentMenu = reservation.getCreatedMenu().get(menuIndex);

            addToQuery = String.format("('%s','%s','%s',%d)", reservation.getReservationCode(),
                    menuIndex, currentMenu.getName(),currentMenu.getnMenuGuests());
            executeQuery(Query.editQuery(Query.INSERT_MENU,addToQuery));

            insertDishesInMenu(reservation,menuIndex,currentMenu);
        }
    }

    private synchronized void insertDishesInMenu(Reservation reservation, int menuIndex, Menu menu){
        String addToQuery;

        for (MenuElement elem : menu.getMenuElementsList()) {
            addToQuery = String.format("('%s',%s,'%s')", reservation.getReservationCode(),
                    menuIndex, elem.getElementCode());
            executeQuery(Query.editQuery(Query.INSERT_DISH_IN_MENU,addToQuery));
        }
    }

    public synchronized void deleteReservation(String resToDeleteCode) {
        Reservation resToDelete = null;

        for(Reservation elem : reservations){
            if (elem.getReservationCode().equals(resToDeleteCode)) {
                resToDelete = elem;
            }
        }
        reservations.remove(resToDelete);

        String addToQuery="WHERE RES_CODE='" + resToDeleteCode + "'";
        executeQuery(Query.editQuery(Query.DELETE_RESERVATION,addToQuery));

    }

    private void executeQuery(String query) {
        DbReader dbr = DbReader.getDbReaderInstance();
        Thread dbThread = new Thread(dbr);
        dbr.setQuery(query);
        dbThread.start();
        try {
            dbThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private String generateReservationId() {
        String tmpId = null;
        boolean unique = false;

        while (!unique) {
            tmpId = generateCode();

            unique = true;
            for (Reservation res : reservations)
                if (tmpId.equalsIgnoreCase(res.getReservationCode()))
                    unique = false;
        }

        return tmpId;
    }

    private String generateCode() {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 6;

        for (int i = 0; i < length; i++)
            sb.append(candidateChars.charAt(random.nextInt(36)));

        return sb.toString();
    }
}
