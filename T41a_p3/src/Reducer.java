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

		@SuppressWarnings("unused")
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
			r = new ThesaurusRecord(fileList.size());
			break;
		default:
			System.out.println("Invalid type of data! " + type);
			System.exit(1);
		}

		// TODO: implement algorithm

		// Create queue
		MinPriorityQueueADT<FileLine> fQ = new FileLinePriorityQueue(fileList.size(), r.getComparator());

		try {
			// Take one entry from each input file and insert into queue
			for (FileIterator fI : fileList) {
				fQ.insert(fI.next());
			}
			// Remove min entry e from queue and merge with r
			r.join(fQ.removeMin());
			// While queue !isEmpty()
			while (!fQ.isEmpty()) {
				FileLine e = fQ.removeMin();
				// compare to key associated with r
				String rKey = r.toString().split(":")[0];
				if (rKey.equals(e.getString().split(":")[0])) {
					// merge e with r
					r.join(e);
				} else {
					
					// write r to output
					System.out.println(r);
					// clear r
					r.clear();
					// merge e with r
					r.join(e);
				}
				// Get the FileIterator from e so that we get the next line from e's file
				FileIterator itr = e.getFileIterator();
				if ( itr.hasNext() ) {
					// get the next line from the same file
					e = itr.next();
					// insert into queue
					fQ.insert(e);
				}
			}
			// write r to output file
			System.out.println(r);

		} catch (PriorityQueueFullException e) {
			System.out.println("Error: Queue already full");
		} catch (PriorityQueueEmptyException e) {
			System.out.println("Error: Queue contains no FileLines");
		}

	}
}
