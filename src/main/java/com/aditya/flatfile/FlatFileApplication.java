package com.aditya.flatfile;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.aditya.flatfile.service.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spring boot class for FlatFileApplication.
 * @author aditya
 */

@RestController
@EnableAutoConfiguration
@ComponentScan("com.aditya.flatfile.service")
public class FlatFileApplication {
	
	private static Logger log = LoggerFactory.getLogger(new FlatFileApplication().getClass());
	
	@RequestMapping("/")
	String home() {
		return "Flat file Application.";
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(FlatFileApplication.class, args);
		
		if(args.length < 2){
			log.error("Please provide input and output file names. Example : java -jar flatfile-service-0.0.1-SNAPSHOT.jar <input file name> <output file name>");
			System.exit(0);
		}
		else {
			String inputFile = args[0];
			String outputFile = args[1];
			
			if(!StringUtils.isEmpty(inputFile) && !StringUtils.isEmpty(outputFile))
			{
				try {
					FileReader fileReader = (FileReader) ctx.getBean("fileReader");
					fileReader.read(inputFile, outputFile);
				} catch(Exception e)
				{
					e.printStackTrace();
					log.error("Exception occured. Please provide input and output file names. Example : java -jar flatfile-service-0.0.1-SNAPSHOT.jar <input file name> <output file name>");
					System.exit(0);
				}
			}
			else
			{
				log.error("Please provide input and output file names. Example : java -jar flatfile-service-0.0.1-SNAPSHOT.jar <input file name> <output file name>");
				System.exit(0);
			}
		}
	}
}