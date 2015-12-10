import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/*
* Copyright (C) 2015 Szilágyi István, ryd.spes@gmail.com
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
* */

public class TripletThread {
	ProcessBuilder tripLength;
	Process tripProcess;
	InputStream tIs;
	InputStreamReader tIsr;
	BufferedReader tBr;
	List<String> commandArg;
	String name;
	
	public TripletThread(String name) {
	    commandArg = new ArrayList<String>();
	    commandArg.add("wc");
	    commandArg.add("-l");
	    commandArg.add(name);
	    this.name = name;
	    tripLength = new ProcessBuilder(commandArg);


	}

	public void readTriplet() {
		{
			try{
				tripProcess = tripLength.start();
			     tIs = tripProcess.getInputStream();
			     tIsr = new InputStreamReader(tIs);
			     tBr = new BufferedReader(tIsr);
			     String tmp = tBr.readLine();
			     File tmpFile = new File(name);
			     if (tmpFile.exists())
				AmmiProcess.input.setText("Mined triplets: " + tmp.split(" ")[0] + 
						" From: " + tmp.split(" ")[1]);
			} catch (IOException e) {
				System.out.println("Error with running the thread");
			}
			
		}
		
	}

}
