package envs.toolkit;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;

public class Shell {
	private ProcessBuilder processBuilder = null;
	private Process process = null;
	private BufferedReader reader = null;

	public Shell(){}
	
	public void execute(String command, boolean quiet) {
		if(!quiet) Prompt.debug("exec: " + ANSIHandler.wrapper(command, 'B')); 
		try{
			processBuilder = new ProcessBuilder();
			processBuilder.command("/bin/bash", "-c", command);
			processBuilder.redirectErrorStream(true);
			
			process = processBuilder.start();
			process.waitFor();
		}
		catch(IOException ioe) {
			ioe.printStackTrace(); System.exit(1);
		}
		catch(InterruptedException ire) {
			ire.printStackTrace(); System.exit(1);
		}
		
		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	}
	public void execute(String command) {execute(command, false);}
	public void execute(String[] cmdarray, boolean quiet) {
		if(!quiet) Prompt.debug("exec: " + ANSIHandler.wrapper(String.join(" ", cmdarray), 'B')); 
		try{
			processBuilder = new ProcessBuilder();
			processBuilder.command("/bin/bash", "-c", String.join(" ", cmdarray));
			processBuilder.redirectErrorStream(true);
			
			process = processBuilder.start();
			process.waitFor();
		}
		catch(IOException ioe) {
			ioe.printStackTrace(); System.exit(1);
		}
		catch(InterruptedException ire) {
			ire.printStackTrace(); System.exit(1);
		}
		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	}
	public void execute(String[] cmdarray) {execute(cmdarray, false);}
	public void executeFrom(String command, File dir, boolean quiet) {
		if(!quiet) Prompt.debug("exec: " + ANSIHandler.wrapper(dir.getAbsolutePath(), 'g') + "$ " + ANSIHandler.wrapper(command, 'B')); 
		try{
			processBuilder = new ProcessBuilder();
			processBuilder.command("/bin/bash", "-c", command);
			processBuilder.directory(dir);
			processBuilder.redirectErrorStream(true);
			
			process = processBuilder.start();
			process.waitFor();
		}
		catch(IOException ioe) {
			ioe.printStackTrace(); System.exit(1);
		}
		catch(InterruptedException ire) {
			ire.printStackTrace(); System.exit(1);
		}
		
		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	}
	public void executeFrom(String command, File dir) {executeFrom(command, dir, false);}
	public void executeFrom(String[] cmdarray, File dir, boolean quiet) {
		if(!quiet) Prompt.debug("exec: " + ANSIHandler.wrapper(dir.getAbsolutePath(), 'g') + "$ " + ANSIHandler.wrapper(String.join(" ", cmdarray), 'B'));  
		try{
			processBuilder = new ProcessBuilder();
			processBuilder.command("/bin/bash", "-c", String.join(" ", cmdarray));
			processBuilder.directory(dir);
			processBuilder.redirectErrorStream(true);
			
			process = processBuilder.start();
			process.waitFor();
		}
		catch(IOException ioe) {
			ioe.printStackTrace(); System.exit(1);
		}
		catch(InterruptedException ire) {
			ire.printStackTrace(); System.exit(1);
		}
		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	}
	public void executeFrom(String[] cmdarray, File dir) {executeFrom(cmdarray, dir, false);}

	// Print the result of the execution on a console
	public void print() throws IOException {
		if(reader == null){
			Prompt.print("Shell", "No command has been executed");
			return;
		}

		String line = reader.readLine();
		while(line != null){
			System.out.println(line);
			line = reader.readLine();
		}
	}

	// Return the result of the execution as an array of strings
	public String[] raw() throws IOException {
		if(reader == null){
			Prompt.print("Shell", "No command has been executed");
			return null;
		}

		ArrayList<String> alist = new ArrayList<String>();
		String line = reader.readLine();
		while(line != null){
			alist.add(line);
			line = reader.readLine();
		}

		String[] ls = new String[alist.size()];
		for(int i = 0; i < alist.size(); i++){
			ls[i] = alist.get(i);
		}

		return ls;
	}

	public void close() throws IOException {reader.close();}

	/*
	public static void exec(String cmd) {
		try{
			Shell sh = new Shell();
			sh.execute(cmd);
			sh.close();
		}
		catch(Exception e) {
			e.printStackTrace(); System.exit(1);
		}
		
	}
	public static void exec(String[] cmd) {
		try{
			Shell sh = new Shell();
			sh.execute(cmd);
			sh.close();
		}
		catch(Exception e) {
			e.printStackTrace(); System.exit(1);
		}
		
	}
	*/
	public static String[] exec(String cmd, boolean quiet) {
		String[] raw = null;
		try{
			Shell sh = new Shell();
			sh.execute(cmd, quiet);
			raw = sh.raw();
			sh.close();
		}
		catch(Exception e) {
			e.printStackTrace(); System.exit(1);
		}
		return raw;
	}
	public static String[] exec(String cmd) {return exec(cmd, false);}
	public static String[] exec(String[] cmd, boolean quiet) {
		String[] raw = null;
		try{
			Shell sh = new Shell();
			sh.execute(cmd, quiet);
			raw = sh.raw();
			sh.close();
		}
		catch(Exception e) {
			e.printStackTrace(); System.exit(1);
		}
		return raw;
	}
	public static String[] exec(String[] cmd) {return exec(cmd, false);}
}
