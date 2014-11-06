package unpackage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import unpackage.Calcul;


public class main {

	public static void main(String[] args) {

		// crÃ©ation d'une liste
		List<AR> maListeAR =  new ArrayList<AR>();
		List<AS> maListeAS =  new ArrayList<AS>();

		try{
    		Class.forName("org.postgresql.Driver");
    	} catch (Exception e) {
    		System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
    	}//Fin catch
		//Création d'un objet de type Connection
    	Connection connect = null;

    	try{
    		String url = "jdbc:postgresql://localhost:5432/tsebillet/taxi";
    		connect = DriverManager.getConnection(url, "t.sebillet", "plopnoobnoob");
    	}catch(Exception e){
    		System.out.println("Une erreur est survenue lors de la connexion à la base de donnée");
    	}

		Statement maRequete = null;
		try {
			maRequete = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String texteRequete = "select * from calcul";
		// définition de l'objet qui récupérera le résultat de l'exécution de la requête :

		ResultSet curseurResultat = null;
		try {
			curseurResultat = maRequete.executeQuery(texteRequete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// récupération des détails du résultats

		try {
			ResultSetMetaData detailsDonnees = curseurResultat.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while(curseurResultat.next()){
				maListeAR.add(new AR(curseurResultat.getInt("dept"), curseurResultat.getDouble("priseCharge"), curseurResultat.getDouble("tarifKMARJS"),
						curseurResultat.getDouble("tarifHoraireJS"), curseurResultat.getDouble("tarifKMARND"), curseurResultat.getDouble("tarifHoraireND")));
				maListeAS.add(new AS(curseurResultat.getInt("dept"), curseurResultat.getDouble("priseCharge"), curseurResultat.getDouble("tarifKMASJS"),
						curseurResultat.getDouble("tarifHoraireJS"), curseurResultat.getDouble("tarifKMASND"), curseurResultat.getDouble("tarifHoraireND")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i;
		/*code enlever pour la v3
		// ajout d'Ã©lÃ©ments Ã  la liste
		for (i=0;i<10;i++){
			maListeAR.add(new AR((int)dept[i][0], dept[i][1], dept[i][4], dept[i][7],
					dept[i][2], dept[i][5]));
			maListeAS.add(new AS((int)dept[i][0], dept[i][1], dept[i][5], dept[i][7],
					dept[i][3], dept[i][6]));
		}
		*/

*/code permettant de lire des données dans un fichier
	String monFichierTarif = ("/home/etudiant/workspace/taxiv2/src/unpackage/docTarif.txt");
try{
//Ouverture d'un flux d'entree à partir du fichier "docTarif.txt"
//Pour la version Windows
BufferedReader monBuffer = new BufferedReader(new FileReader(monFichierTarif));
String line = null; //Variable qui contiendra chaque ligne du fichier
//Tant qu'il reste une ligne au fichier
while ((line = monBuffer.readLine()) != null)
{
//On découpe la ligne gràce au caractère ","
String[] part = line.split(",");
//On ajoute un objet de la classe Tarif à la brochure, à partir de la ligne du fichier découpée
maListeAR.add(new AR(Integer.parseInt(part[0]),Double.parseDouble(part[1]),Double.parseDouble(part[4]),Double.parseDouble(part[7]),
Double.parseDouble(part[2]),Double.parseDouble(part[5])));
maListeAS.add(new AS(Integer.parseInt(part[0]),Double.parseDouble(part[1]),Double.parseDouble(part[5]),Double.parseDouble(part[7]),
Double.parseDouble(part[3]),Double.parseDouble(part[6])));
}
//Fermeture du buffer
monBuffer.close();
} catch (Exception e) {
System.out.println("Fichier contenant les tarifs introuvable !!!");
System.exit(0);
}
*/
		Saisie maSaisie = new Saisie();

		boolean saisieOK = false;

		do{

			boolean trouve = false;
			i = 0;

			while(!trouve && i<maListeAR.size()){
				if(maSaisie.getNumDept()==maListeAR.get(i).getDept()){
					trouve = true;
				}else{
					i++;
				}
			}

			if(trouve){
				saisieOK = true;
			}
			else{
				Scanner deptObjet = new Scanner(System.in);
				System.out.println("Département non trouvé; veuillez resaisir");
				maSaisie.setNumDept(deptObjet.nextInt());
			}
		}while(!saisieOK);

		System.out.println("Résultat : " + Calcul.calculer(i, maListeAR, maListeAS, maSaisie));

	}

}
