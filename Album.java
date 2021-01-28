package activity3;

import java.util.*;

/**
 * Represents an album with a title (mandatory) and 
 * artist (can be absent). An album can be incomplete,
 * in the sense that some tracks can be missing.
 */
public class Album implements Iterable<Song>
{
    private String aTitle;
	private Artist aArtist;
	private Map<Integer, Optional<Song>> aTracks = new HashMap<>();
	
	public Album(String pTitle) {
		assert pTitle != null;
		aTitle = pTitle;
		aArtist = Artist.get("Absent", new ArrayList<String>());
	}

	public Album(String pTitle, Optional<Artist> pArtist, Map<Integer, Optional<Song>> pTracks) {
		assert aTitle != null;
		aTitle = pTitle;
		aTracks = pTracks;
	}

	public void removeSong(Song song) {
		aTracks.remove(song.getFile(), song);
	}

	public Iterator<Song> iterator()
	{
		return (Iterator<Song>) aTracks.values();
	}

	public String getTitle() {
		return aTitle;
	}
	
	public void setTitle(String pTitle) {
		aTitle = pTitle;
	}
	
	public Artist getArtist() {
		return aArtist;
	}
	
	public void setArtist(Artist pArtist) {
		aArtist = pArtist;
	}
	/**
	 * Add a track to this album. If the track already exists,
	 * it is written over.
	 * 
	 * @param pNumber The track number. Must be greater than 0.
	 * @param pSong The song for this track. 
	 * @pre pNumber > 0 && pSong != null
	 */
	public void addTrack(int pNumber, Song pSong)
	{
		assert pNumber > 0 && pSong != null;
		aTracks.put(pNumber, Optional.of(pSong));
	}

	public Map<Integer, Optional<Song>> getaTracks()
	{
		return aTracks;
	}
	
	// To check if two objects have the same title, artist, and tracks in the same order.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (aArtist == null) {
			if (other.aArtist != null)
				return false;
		} else if (!aArtist.equals(other.aArtist))
			return false;
		if (!aTitle.equals(other.aTitle))
			return false;
		if (aTracks == null) {
			if (other.aTracks != null)
				return false;
		} else if (!aTracks.equals(other.aTracks))
			return false;
		return true;
	}
	
	// To check if two objects have the same songs, but but order and title/artist do not matter.
		public boolean contentEquals(Object obj) {
		
		 Set<Song> set1 = new HashSet<>();
		 Iterator<Map.Entry<Integer, Optional<Song>>> it1 = aTracks.entrySet().iterator();
		 while(it1.hasNext())
		 {
			 Map.Entry<Integer, Optional<Song>> entry1 = it1.next();
 	    	 set1.add(entry1.getValue().get());
		 }
		 
		 Set<Song> set2 = new HashSet<>();
		 Iterator<Map.Entry<Integer, Optional<Song>>> it2 = ((Album) obj).getaTracks().entrySet().iterator();
		 while(it2.hasNext())
		 {
			 Map.Entry<Integer, Optional<Song>> entry2 = it2.next();
			 set2.add(entry2.getValue().get());
		 }
		 
		 if(set1.equals(set2)){
	        return true;
		} 
		 	return false;
	}

}
