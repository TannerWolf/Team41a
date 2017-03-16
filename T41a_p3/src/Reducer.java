/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p3
// FILE:             Reducer
//
// TEAM:    Team 41a
// Authors: 
// Author1: (Jiayue Lai, jlai28@wisc.edu, jlai, Lec 002)
// Author2: (William Mustari, willmustari@gmail.com, mustari, Lec 002) 
// Author3: (Tanner Wolf, tmwolf2@wisc.edu, tmwolf2, Lec 002) 
///////////////////////////////////////////////////////////////////////////////
import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Reducer solves the following problem: given a set of sorted input files (each
 * containing the same type of data), merge them into one sorted file.
 *
 */
public class Reducer {
	// list of files for stocking the PQ
	private List<FileIterator> fileList;
	private String type, dirName, outFile;

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java Reducer <weather|thesaurus> <dir_name> <output_file>");
			System.exit(1);
		}

		String type = args[0];
		String dirName = args[1];
		String outFile = args[2];

		Reducer r = new Reducer(type, dirName, outFile);
		r.run();

	}

	/**
	 * Constructs a new instance of Reducer with the given type (a string
	 * indicating which type of data is being merged), the directory which
	 * contains the files to be merged, and the name of the output file.
	 */
	public Reducer(String type, String dirName, String outFile) {
		this.type = type;
		this.dirName = dirName;
		this.outFile = outFile;
	}

	/**
	 * Carries out the file merging algorithm described in the assignment
	 * description.
	 */
	public void run() {
		File dir = new File(dirName);
		File[] files = dir.listFiles();
		Arrays.sort(files);

		Record r = null;

		// list of files for stocking the PQ
		fileList = new ArrayList<FileIterator>();

		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isFile() && f.getName().endsWith(".txt")) {
				fileList.add(new FileIterator(f.getAbsolutePath(), i));
			}
		}

		switch (type) {
		case "weather":
			r = new WeatherRecord(fileList.size());
			break;
		case "thesaurus":
			r = new ThesaurusRecord (fileList.size());
			break;
		default:
			System.out.println("Invalid type of data! " + type);
			System.exit(1);
		}

		// Create queue
		MinPriorityQueueADT<FileLine> fQ = new FileLinePriorityQueue(fileList.size(), r.getComparator());

		try {
			// Take one entry from each input file and insert into queue
			for (FileIterator fI : fileList) {
				fQ.insert(fI.next());
			}
			
			// Create output file and writer
			File f = new File(outFile);
			PrintWriter writer;
			writer = new PrintWriter( new BufferedWriter( new FileWriter(f)));
			
			// Remove min entry e from queue and merge with r
			FileLine e = fQ.removeMin();
			r.join(e);
			// Get the FileIterator from e so that we get the next line from e's file
			FileIterator itr = e.getFileIterator();
			if ( itr.hasNext() ) {
				// get the next line from the same file
				e = itr.next();
				// insert into queue
				fQ.insert(e);
			}
			
			// While queue !isEmpty()
			while (!fQ.isEmpty()) {
				e = fQ.removeMin();
				// compare to key associated with r
				String rKey = null;
				String newKey = null;
				if (type.equals("thesaurus")) {
					rKey = r.toString().split(":")[0];
					newKey = e.getString().split(":")[0];
				}
				else if (type.equals("weather")) {
					String st[] = r.toString().split(",");
					rKey = st[0] + "," + st[1];
					st = e.getString().split(",");
					newKey = st[0] + "," + st[1];
				}
				
				if (rKey.equals(newKey)) {
					// merge e with r
					r.join(e);
				} else {
					// write r to output
					writer.println(r);
					// clear r
					r.clear();
					// merge e with r
					r.join(e);
				}
				// Get the FileIterator from e so that we get the next line from e's file
				itr = e.getFileIterator();
				if ( itr.hasNext() ) {
					// get the next line from the same file
					e = itr.next();
					// insert into queue
					fQ.insert(e);
				}
			}
			// write r to output file for last key
			writer.println(r);
			// flush and close writer
			writer.flush();
			writer.close();

		} catch (PriorityQueueFullException e) {
			System.out.println("Error: Queue already full");
		} catch (PriorityQueueEmptyException e) {
			System.out.println("Error: Queue contains no FileLines");
		} catch (IOException e) {
			System.out.println("Error creating output file");
		}

	}
}