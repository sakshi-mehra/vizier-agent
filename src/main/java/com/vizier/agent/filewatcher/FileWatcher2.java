/**
 * 
 */
package com.vizier.agent.filewatcher;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import com.vizier.client.VizierBackendClient;
import com.vizier.state.StateHandler;

/**
 * @author aniruddha
 *
 */
public class FileWatcher2 extends FileAlterationListenerAdaptor{

	@Override
	public void onFileChange(final File file) {
		if(file.getName().contentEquals("state.json")) {
			try {
				StateHandler.refresh(file.getPath());
				if(StateHandler.getState().isWatcherActive()) {
					System.out.println("State json alerted to stop watching file");
					FileWatcherHandler.getMonitor().stop();
				}			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(file.getName().contentEquals("temp.py")) {
			System.out.println("Python file changes, sync with backend");
		}
	}

}
