package com.tfg.wekaWeb.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.tfg.wekaWeb.dao.FicherosRepository;
import com.tfg.wekaWeb.dto.Algoritmos;
import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.dto.Filtros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

@Service
public class FicherosService {

	@Autowired
	private FicherosRepository ficherosRepository;

	static ApplicationHome app = new ApplicationHome();
	static File home = app.getDir();
	static File datasets = new File("C:\\tmpFiles", "datasets");
	private static String UPLOADED_FOLDER = datasets.getAbsolutePath();

	private List<Ficheros> ficheros;
	
	public Ficheros guardarFichero(String ContentType, String OriginalName, Integer idSessionActual,String comentario) throws Exception {
		
		File path = new File(UPLOADED_FOLDER, OriginalName);
		String filename = OriginalName;
		Ficheros fichero = null;
		
		if(filename.contains(".arff")) {
			//String path = UPLOADED_FOLDER + file.getOriginalFilename();
			String [] datosDataset = datosDataset(path.getAbsolutePath());
			 fichero = new Ficheros(ContentType, filename, path.getAbsolutePath(), idSessionActual, new Date(),
					new Date(),comentario,null,null,null);
			 ficherosRepository.save(fichero);
		}
		else {
			
		String[] fileNameArff = filename.split("\\.");
		filename = fileNameArff[0] + ".arff";
		try {
			if (filename.contains("..")) {
				throw new FileSystemException("El fichero contiene carácterés inválidos");
			}
			String pathCSV = convertirCSVtoARFF(path.getAbsolutePath());
			
			 fichero = new Ficheros(ContentType, filename, pathCSV, idSessionActual, new Date(),
					new Date(),comentario,null,null,null);
			 ficherosRepository.save(fichero);
		
		} catch (IOException ex) {
			throw new FileSystemException("No se pudo guardar");
		}
		}
		
		String [] datosDataset = datosDataset(fichero.getRuta());
		String clase = datosDataset[1];
		String nAtributos = datosDataset[2];
		String nInstancias = datosDataset[0];
		fichero.setClase(clase);
		fichero.setnAtributos(nAtributos);
		fichero.setNumInstancias(nInstancias);
		fichero =  ficherosRepository.save(fichero);
		return fichero;
	}


	public Optional<Ficheros> getFichero(int idFichero) {
		return ficherosRepository.findById(idFichero);
	}
	
	public List<Ficheros> getFicherosByIdSession(int idSession){
		return ficherosRepository.findAllByIdSession(idSession);
	} 
	
	public File getFileByFichero(Integer idFichero) {
		Optional<Ficheros> fichero = ficherosRepository.findById(idFichero);
		File file = new File(fichero.get().getRuta());
		return file;
	}
	
	public List<Ficheros> getFiles(){ 
		return ficherosRepository.findAllFicheros();
	};
	
	public void deleteFicherosById(int idFichero) {
		 ficherosRepository.deleteById(idFichero);
	}
	
	public String convertirCSVtoARFF(String sourcepath) throws Exception
    {
        // load CSV
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(sourcepath));
        Instances dataSet = loader.getDataSet();
        String[] destpath = sourcepath.split("\\.");
        String sourceDest =destpath[0]+".arff";
        // save ARFF
        BufferedWriter writer = new BufferedWriter(new FileWriter(sourceDest));
        writer.write(dataSet.toString());
        writer.flush();
        writer.close(); 
        return sourceDest;
	}
	
	public String[] datosDataset(String path) throws IOException{
		/* Cargamos el fichero con los datos */
		BufferedReader datafile = new BufferedReader(new FileReader(path));
		Instances data = new Instances(datafile);
		String[] datos = new String[3];
		/* Seleccionamos la columna de los datos a estimar */
		data.setClassIndex(data.numAttributes() - 1);
		List<String> list = new ArrayList<>();
		Enumeration<Attribute> e = data.enumerateAttributes();
		while(e.hasMoreElements()){
			list.add(e.nextElement().toString());
		}
		
		datos[0]= String.valueOf(list.size());
		String classAtribute = data.classAttribute().toString(); 
		if(classAtribute.contains(Pattern.quote("{"))){
			String[] clase = classAtribute.split(Pattern.quote("{"));
			clase = clase[1].split(Pattern.quote("}"));
			datos[1]=clase[0];
		}else{
			datos[1]="No tiene";
		}
		
		datos[2]= String.valueOf(data.numInstances());
		return datos;
	}
	
	public String generatePDF(String resultados, Ficheros file, Filtros filtro, Algoritmos alg) throws IOException, DocumentException {
		
		// Se crea el documento
		Document document = new Document();
	      File tempFile = File.createTempFile("result.pdf", ".tmp");
		// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
		FileOutputStream ficheroPdf = new FileOutputStream(tempFile);
		// Se asocia el documento al OutputStream y se indica que el espaciado entre
		// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
		PdfWriter pdfWriter = PdfWriter.getInstance(document,ficheroPdf);
		
		pdfWriter.setLinearPageMode();
        pdfWriter.setFullCompression();


        // document header attributes
        document.addAuthor("WekaWeb");
        document.addCreationDate();
        document.addProducer();
        document.addCreator("WekaWeb");
        document.addTitle("");
        document.setPageSize(PageSize.A4);
		

		String html = "<h1><img alt=\"\" src=\"C:\\Users\\Jose\\Documents\\repositorio\\src\\main\\resources\\static\\images\\weka-min.png\" style=\"width: 90px; height: 90px;\" />&nbsp; &nbsp; Resultados del an&aacute;lisis con la herramienta weka&nbsp;</h1>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p>A continuaci&oacute;n se resumir&aacute; las propiedades aplicadas en el proceso de miner&iacute;a de datos:</p>\r\n" + 
				"\r\n\n" + 
				"<p><strong>Fichero</strong>: "+ file.getNombreFichero() +" <strong>Numero de atributos</strong>: "+file.getNumInstancias()+" <strong>Numero de instancias</strong>: "+file.getnAtributos()+" <strong>Clase</strong>: "+file.getClase()+" <strong>Comentario</strong>: "+file.getComentario()+"</p>\r\n" + 
				"\r\n\n" + 
				"<p><strong>Tipo de filtro</strong>: "+filtro.getTipo()+" <strong>Atributos eliminados:</strong>"+filtro.getAtributosRemoveName()+"</p>\r\n" + 
				"\r\n\n" + 
				"<p><strong>Algorimo</strong>: "+alg.getNombreAlg()+"</p>\r\n" + 
				"\r\n\n" + 
				"<h3 style=\"color:#aaa;font-style:italic;\">Resultados del proceso:</h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n";
				
		
		document.open();
        XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
        byte[] byteArray = html.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        worker.parseXHtml(pdfWriter, document, byteArrayInputStream, Charset.forName("UTF-8"));
		document.add(new Paragraph(resultados));
		document.close();
		pdfWriter.close();
		return tempFile.getAbsolutePath();
	}
	
}
