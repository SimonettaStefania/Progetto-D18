package services;

public class Query {
    public static final String SELECT_ALL_DISHES = "SELECT * FROM DISHES";
    public static final String SELECT_ALL_ALLERGENS = "SELECT * FROM ALLERGENS";
    public static final String SELECT_STARTERS = "SELECT * FROM STARTERS";
    public static final String SELECT_FIRST_COURSES = "SELECT * FROM FIRST_COURSES";
    public static final String SELECT_MAIN_COURSES = "SELECT * FROM MAIN_COURSES";
    public static final String SELECT_DESSERTS = "SELECT * FROM DESSERTS";
    public static final String SELECT_DRINKS = "SELECT * FROM DRINKS";
    public static final String SELECT_ALLERGENS_IN_DISHES = "SELECT * FROM ALLERGENS_IN_DISHES NATURAL JOIN ALLERGENS";
    public static final String SELECT_INGREDIENTS_IN_DISHES = "SELECT * FROM INGREDIENTS_IN_DISHES NATURAL JOIN INGREDIENTS";
    public static final String INSERT_RESERVATION= "INSERT INTO RESERVATIONS VALUES ";
    public static final String SELECT_RESERVATION= "SELECT * FROM  RESERVATIONS ";
    public static final String DELETE_RESERVATION= "DELETE FROM  RESERVATIONS ";
    private static final String[] FLAG_TYPE = {"VEGAN", "VEGETARIAN", "CELIAC"};
    public Query(){

    }

    public static String editQuery(String query, String stringToAdd){
        String newQuery=query + stringToAdd;
        return newQuery;
    }
}
