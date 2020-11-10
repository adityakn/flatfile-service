package com.aditya.flatfile.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aditya.flatfile.resource.Entry;
import com.aditya.flatfile.resource.FlatFileData;
import com.aditya.flatfile.util.FlatFileUtil;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * FileReader service to read a given flat file and build line entries and errors.
 * @author aditya
 */

@Service
public class FileReader {
	
	private static Logger log = LoggerFactory.getLogger(new FileReader().getClass());
	public FlatFileData flatFileData = new FlatFileData();

	/**
	 * Method for reading input file and save out put to file.
	 * @param inputFile
	 * @param outputFile
	 */
	public void read(String inputFile, String outputFile) {
		try {
			List<String> list = new ArrayList<>();

			try (Stream<String> stream = Files.lines(Paths.get(inputFile))) {
				list = stream.collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}

			int lineCounter = 1;
			List<String> errorLineList = new ArrayList<String>();
			List<Entry> entryLineList = new ArrayList<Entry>();
			for (String s : list) {
				if (FlatFileUtil.isValidLine(s)) {
					entryLineList.add(buildEntry(s));
				} else {
					errorLineList.add(String.valueOf(lineCounter));
				}
				lineCounter++;
			}

			entryLineList.sort(Comparator.comparing(Entry::getLastname).thenComparing(Entry::getFirstname));
			
			flatFileData.setEntries(entryLineList.toArray(new Entry[entryLineList.size()]));
			flatFileData.setErrors(errorLineList.toArray(new String[errorLineList.size()]));

			ObjectMapper mapper = new ObjectMapper();
			DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter("  ", DefaultIndenter.SYS_LF);
			DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
			printer.indentObjectsWith(indenter);
			printer.indentArraysWith(indenter);
			mapper.writer(printer).writeValue(new File(outputFile), flatFileData);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method to build Entry object for a given line String.
	 * @param line
	 * @return Entry
	 */
	public Entry buildEntry(String line) {
		log.debug("line : " + line);
		
		Entry entry = new Entry();
		try {
			String[] strList = line.split(",");
			int length = strList.length;
			//Checking for phone number values
			if (length == 5)
			{
				if(FlatFileUtil.isPhone(strList[2]))
				{
					log.debug("LastName, FirstName, (703)-711-0996, Blue, 11013 - format");
					entry.setLastname(strList[0].trim());
					entry.setFirstname(strList[1].trim());
					entry.setPhonenumber(strList[2].replaceAll("\\D+",""));
					entry.setColor(strList[3].trim());
					entry.setZipcode(strList[4].trim());
					
				}else if(FlatFileUtil.isPhone(strList[3])) {
					log.debug("FirstName, LastName, 12023, 636 121 1111, Yellow - format");
					entry.setFirstname(strList[0].trim());
					entry.setLastname(strList[1].trim());
					entry.setZipcode(strList[2].trim());
					entry.setPhonenumber(strList[3].replaceAll("\\D+",""));
					entry.setColor(strList[4].trim());
				}
			} else if (length == 4) { 
				log.debug("FirstName LastName, Purple, 14537, 713 905 0383 - format");
				String[] nameStrList = strList[0].split(" ");
				entry.setFirstname(nameStrList[0].trim());
				entry.setLastname(nameStrList[1].trim());
				entry.setColor(strList[1].trim());
				entry.setZipcode(strList[2].trim());
				entry.setPhonenumber(strList[3].replaceAll("\\D+",""));
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return entry;
	}
}