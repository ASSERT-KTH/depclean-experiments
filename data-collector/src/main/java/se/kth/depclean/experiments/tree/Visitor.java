package se.kth.depclean.experiments.tree;

public interface Visitor {

    public void visit(Node tree) throws VisitException;

}
