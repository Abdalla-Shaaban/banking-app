package com.springboot.batch;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.springboot.parser.DomParser;

public class BatchAccounts {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
			
		File dir = new File("C:\\Java\\bank-accounts");
		Path path = dir.toPath();

		// Sanity check - Check if path is a folder
		try {

			Boolean isFolder = (Boolean) Files.getAttribute(path, "basic:isDirectory", NOFOLLOW_LINKS);
			if (!isFolder) {
				throw new IllegalArgumentException("Path: " + path + " is not a folder");
			}
		} catch (IOException ioe) {
			// Folder does not exists
			ioe.printStackTrace();
		}

		System.out.println("Watching path: " + path);

		// We obtain the file system of the Path
		FileSystem fs = path.getFileSystem();

		// We create the new WatchService using the new try() block
		try (WatchService service = fs.newWatchService()) {

			// We register the path to the service
			// We watch for creation events
			path.register(service, ENTRY_CREATE);

			// Start the infinite polling loop
			WatchKey key = null;
			while (true) {
				key = service.take();

				// Dequeuing events
				Kind<?> kind = null;
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					// Get the type of the event
					kind = watchEvent.kind();
					if (OVERFLOW == kind) {
						continue; // loop
					} else if (ENTRY_CREATE == kind) {
						// A new Path was created
						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						// Output
						System.out.println("New path created: " + newPath);
						Runnable r1 = new DomParser(newPath);
						Runnable r2 = new DomParser(newPath);
						Runnable r3 = new DomParser(newPath);
						Runnable r4 = new DomParser(newPath);
						Runnable r5 = new DomParser(newPath);
						ExecutorService pool = Executors.newFixedThreadPool(3);   
						pool.execute(r1);
						pool.execute(r2);
						pool.execute(r3);
						pool.execute(r4);
						pool.execute(r5);
						pool.shutdown();  
					}
				}

				if (!key.reset()) {
					break; // loop
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
