package pomocni;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFajlova {
	
	public static void praviZipFajl() {

        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yy_MM_dd_hh_mm");
			String imeFajla = sdf.format(new Date());
            FileOutputStream fos = new FileOutputStream("backup_"+imeFajla+".zip");
            ZipOutputStream zipOS = new ZipOutputStream(fos);
			
			File fajl1 = new File("events");
			File fajl2 = new File("fajlovi");
			File fajl3 = new File("serijal");
			File[] nizFajlova1 = fajl1.listFiles();
			File[] nizFajlova2 = fajl2.listFiles();
			File[] nizFajlova3 = fajl3.listFiles();
			
			for(File f: nizFajlova1){
				pisiUZipFajl(f.getPath(), zipOS);
			}
			for(File f: nizFajlova2){
				pisiUZipFajl(f.getPath(), zipOS);
			}
			for(File f: nizFajlova3){
				pisiUZipFajl(f.getPath(), zipOS);
			}
			System.out.println("Uradjena je kopija svih tekstualnih fajlova!");
            zipOS.close();
            fos.close();

        } catch (FileNotFoundException e) {
        	LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
        } catch (IOException e) {
        	LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
        }

    }

    
    public static void pisiUZipFajl(String putanja, ZipOutputStream zipStream)
            throws FileNotFoundException, IOException {

        File aFile = new File(putanja);
        FileInputStream fis = new FileInputStream(aFile);
        ZipEntry zipEntry = new ZipEntry(putanja);
        zipStream.putNextEntry(zipEntry);

        byte[] bajtovi = new byte[1024];
        int duzina;
        while ((duzina = fis.read(bajtovi)) >= 0) {
            zipStream.write(bajtovi, 0, duzina);
        }

        zipStream.closeEntry();
        fis.close();
    }

}
