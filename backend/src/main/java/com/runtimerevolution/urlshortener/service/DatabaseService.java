package com.runtimerevolution.urlshortener.service;

public interface DatabaseService {
  long getNext(String sequenceId);
}
