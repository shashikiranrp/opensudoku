package org.kelvin.opensudoku.solver;

import org.kelvin.opensudoku.VirtualSudokuBoard;
import org.kelvin.opensudoku.concrete.Concrete;
import org.kelvin.opensudoku.exceptions.ConcreteNotNeededException;
import org.kelvin.opensudoku.exceptions.InvalidBoxEntryException;
import org.kelvin.opensudoku.exceptions.InvalidDimensionException;
import org.kelvin.opensudoku.exceptions.NoMorePossibilitiesException;
import org.kelvin.opensudoku.exceptions.UnIntializedParameterException;
import org.kelvin.opensudoku.fon.Fon;

/**
 * 
 * @author Shashikiran
 */
public class V2S_Solver
{

    public static V2S_Results returnResults(VirtualSudokuBoard vsb) throws UnIntializedParameterException, ConcreteNotNeededException, NoMorePossibilitiesException, InvalidDimensionException, InvalidBoxEntryException
    {
        return new V2S_Results((new Concrete((new Fon(vsb)).doFon())).doConcrete());
    }
}
