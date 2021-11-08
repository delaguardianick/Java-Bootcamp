package com.sg.dvdcollection.dao;
import com.sg.dvdcollection.dto.Dvd;
import java.util.List;

/**
 *
 * Interface for the Data Access Object (DAO)
 */
public interface dvdCollectionDao {
    
    /**
     * Adds the given dvd to the collection and associates it with the given
     * title. If there is already a dvd associated with the given
     * title it will return that dvd object, otherwise it will
     * return null.
     *
     * @param title title of dvd with which dvd is to be associated
     * @param dvd dvd to be added to the roster
     * @return the dvd object previously associated with the given
     * dvd id if it exists, null otherwise
     */
    Dvd addDvd(String title, Dvd dvd) throws DvdCollectionDaoException;
    
//  @return collection of DVDs
    List<Dvd> getAllDvds() throws DvdCollectionDaoException;
    
//  @return removed dvd object
    Dvd removeDvd(String title) throws DvdCollectionDaoException;
    
//  @return requested dvd object
    Dvd searchDvd(String title) throws DvdCollectionDaoException;
    
//  loads the DVD collection from an external file into the app
    void loadCollection() throws DvdCollectionDaoException;
    
//    transforms a DVD marshalled as a line of text into a Dvd object
    Dvd unmarshallDvd(String dvdAsText);
    
//  saves the DVD collection in the app into an external file
    void saveCollection() throws DvdCollectionDaoException;
    
//    transforms a Dvd objectDVD into a marshalled line of text  
    String marshallStudent(Dvd dvdAsObject);
    
    
}
