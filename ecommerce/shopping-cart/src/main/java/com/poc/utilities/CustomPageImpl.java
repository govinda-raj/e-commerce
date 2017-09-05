package com.poc.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Created by Govinda on 01/08/17.
 */

public class CustomPageImpl<Product> extends PageImpl<Product> {
    private static final long serialVersionUID = 1L;
    private int number;
    private int size;
    private int totalPages;
    private int numberOfElements;
    private long totalElements;
    //private boolean previousPage;
    private boolean first;
    //private boolean nextPage;
    private boolean last;
    private List<Product> content;
    private Sort sort;
    //private com.poc.utilities.Sort sort;



   public CustomPageImpl() {
        super((List<Product>) new ArrayList<>());
    }

   @Override
    public int getNumber() {
        return number;
    }

   public void setNumber(int number) {
        this.number = number;
    }

   @Override
    public int getSize() {
        return size;
    }

   public void setSize(int size) {
        this.size = size;
    }

   @Override
    public int getTotalPages() {
        return totalPages;
    }

   public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

   @Override
    public int getNumberOfElements() {
        return numberOfElements;
    }

   public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

   @Override
    public long getTotalElements() {
        return totalElements;
    }

   public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

//    public boolean isPreviousPage() {
//        return previousPage;
//    }
//
//    public void setPreviousPage(boolean previousPage) {
//        this.previousPage = previousPage;
//    }

   public boolean isFirstPage() {
        return first;
    }

   public void setFirstPage(boolean firstPage) {
        this.first = firstPage;
    }


   public boolean isLastPage() {
        return last;
    }

   public void setLastPage(boolean lastPage) {
        this.last = lastPage;
    }

   @Override
    public List<Product> getContent() {
        return content;
    }

   public void setContent(List<Product> content) {
        this.content = content;
    }

   @Override
    public Sort getSort() {
        return sort;
    }

   public void setSort(Sort sort) {

       this.sort = sort;
    }


   public Page<Product> pageImpl() {
        return new PageImpl<>(getContent(), new PageRequest(getNumber(),
                getSize(), getSort()), getTotalElements());
    }
}