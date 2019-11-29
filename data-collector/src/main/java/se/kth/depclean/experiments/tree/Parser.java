package se.kth.depclean.experiments.tree;


import java.io.Reader;


public interface Parser {

    Node parse(Reader reader) throws ParseException;

}
