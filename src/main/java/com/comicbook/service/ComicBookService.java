package com.comicbook.service;

import com.comicbook.domain.ComicBook;
import com.comicbook.repository.ComicBookRepository;
import com.comicbook.service.dto.ComicBookDTO;
import com.comicbook.service.mapper.ComicBookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ComicBook.
 */
@Service
@Transactional
public class ComicBookService {

    private final Logger log = LoggerFactory.getLogger(ComicBookService.class);
    
    @Inject
    private ComicBookRepository comicBookRepository;

    @Inject
    private ComicBookMapper comicBookMapper;

    /**
     * Save a comicBook.
     *
     * @param comicBookDTO the entity to save
     * @return the persisted entity
     */
    public ComicBookDTO save(ComicBookDTO comicBookDTO) {
        log.debug("Request to save ComicBook : {}", comicBookDTO);
        ComicBook comicBook = comicBookMapper.comicBookDTOToComicBook(comicBookDTO);
        comicBook = comicBookRepository.save(comicBook);
        ComicBookDTO result = comicBookMapper.comicBookToComicBookDTO(comicBook);
        return result;
    }

    /**
     *  Get all the comicBooks.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ComicBookDTO> findAll() {
        log.debug("Request to get all ComicBooks");
        List<ComicBookDTO> result = comicBookRepository.findAll().stream()
            .map(comicBookMapper::comicBookToComicBookDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one comicBook by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ComicBookDTO findOne(Long id) {
        log.debug("Request to get ComicBook : {}", id);
        ComicBook comicBook = comicBookRepository.findOne(id);
        ComicBookDTO comicBookDTO = comicBookMapper.comicBookToComicBookDTO(comicBook);
        return comicBookDTO;
    }

    /**
     *  Delete the  comicBook by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ComicBook : {}", id);
        comicBookRepository.delete(id);
    }
}
