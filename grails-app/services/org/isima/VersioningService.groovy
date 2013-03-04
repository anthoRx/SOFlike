package org.isima

import java.sql.Timestamp

class VersioningService {
	
	/**
	 * This method allows to version a content of an IntercationContent object like a 
	 * question or an answer for example. 
	 * 
	 * @param object The object which must be versioned
	 * @param oldContent The content o version
	 * @return nothing
	 */
    def versionContent(InteractionContent object, String oldContent) {
		if(oldContent != null && !oldContent.isEmpty()) {
			def date = new Timestamp(new Date().getTime())
			def vers = new Versioning(content: oldContent, modificationDate: date)
			object.versionings.add(vers);
			return vers
		}
    }
}
