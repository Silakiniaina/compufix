package model.utils;

import java.time.Year;
import java.util.stream.IntStream;

import model.recommandation.ComposantRecommande;

public class GeneralUtils {
    
    public static int[] generateYears(int minYear) {
        int currentYear = Year.now().getValue();

        // Vérifie que minYear est valide
        if (minYear > currentYear) {
            throw new IllegalArgumentException("The minimum year cannot be greater than the current year.");
        }

        // Génère un tableau contenant toutes les années entre minYear et currentYear
        return IntStream.rangeClosed(minYear, currentYear).toArray();
    }

    public static String generateYearSelectHTML(int minYear) {
        int[] years = generateYears(minYear);
    
        // Construire le HTML en utilisant StringBuilder
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"col-md-4 form-group mb-3\">\n")
            .append("    <label for=\"Annee\">Annee</label>\n")
            .append("    <select id=\"Annee\" name=\"annee\" class=\"form-control w-100\">\n")
            .append("        <option value=\"0\">All</option>\n");
    
        for (int year : years) {
            html.append("        <option value=\"").append(year).append("\">").append(year).append("</option>\n");
        }
    
        html.append("    </select>\n</div>");
        return html.toString();
    }

    public static String generateSelectYearRecommandation(){
        String result = null;
        try {
            int minYear  = ComposantRecommande.getMinYear(null);
            result = generateYearSelectHTML(minYear);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }
}
