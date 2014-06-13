package org.kelvin.opensudoku.concrete;

import java.util.Vector;
import org.kelvin.opensudoku.exceptions.ConcreteNotNeededException;
import org.kelvin.opensudoku.exceptions.InvalidBoxEntryException;
import org.kelvin.opensudoku.exceptions.InvalidDimensionException;
import org.kelvin.opensudoku.exceptions.NoMorePossibilitiesException;
import org.kelvin.opensudoku.exceptions.UnIntializedParameterException;
import org.kelvin.opensudoku.fon.FonResults;
import org.kelvin.opensudoku.fon.VoidClass;

/**
 * 
 * @author Shashikiran
 */
public class Concrete
{

    private FonResults fonResults;
    private Vector<Object> concreteVoidVector;

    public Concrete()
    {
        this.fonResults = null;
        this.concreteVoidVector = null;
    }

    public Concrete(FonResults fr)
    {
        this.fonResults = fr;
        this.concreteVoidVector = null;
    }

    public ConcreteResults doConcrete() throws UnIntializedParameterException, ConcreteNotNeededException, NoMorePossibilitiesException, InvalidDimensionException, InvalidBoxEntryException
    {
        int x;
        if (this.fonResults == null) {
            throw new UnIntializedParameterException("\'Concrete.FCV\'");
        }
        if (!this.fonResults.concreteFlag) {
            throw new ConcreteNotNeededException();
        }
        if ((x = this.test4Concrete()) != -1) {
            throw new NoMorePossibilitiesException(x);
        }

        this.concreteVoidVector = this.lcfVoidVectorConstructor();

        ConcreteAnalyser v = new ConcreteAnalyser(this.concreteVoidVector, this.fonResults.fonSolutionBoard);
        return new ConcreteResults(v.doConcreteAnalysis(), this.fonResults);
    }

    private int test4Concrete()
    {
        for (Object ob : this.fonResults.fonAfterVoids) {
            if (((VoidClass) ob).getPossibilityCount() == 0) {
                return ((VoidClass) ob).getMySquare();
            }
        }
        return -1;
    }

    //Constructing LCF void vector as a Priority Queue
    private Vector<Object> lcfVoidVectorConstructor()
    {
        int i, j;
        Vector<Object> temp = new Vector<Object>();
        first:
        for (Object ob : this.fonResults.fonAfterVoids) {
            j = ((VoidClass) ob).getPossibilityCount();
            for (i = 0; i < temp.size(); i++) {
                if (j < ((VoidClass) temp.get(i)).getPossibilityCount()) {
                    ((VoidClass) ob).setPosList(((VoidClass) ob).getOnlyPossList());
                    temp.insertElementAt(ob, i);
                    continue first;
                }
            }
            if (i == temp.size()) {
                ((VoidClass) ob).setPosList(((VoidClass) ob).getOnlyPossList());
                temp.insertElementAt(ob, i);
            }
        }
        return temp;
    }
}
