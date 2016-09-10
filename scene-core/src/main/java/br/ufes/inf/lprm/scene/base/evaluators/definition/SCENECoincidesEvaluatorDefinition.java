package br.ufes.inf.lprm.scene.base.evaluators.definition;

import br.ufes.inf.lprm.scene.base.evaluators.implementation.SCENECoincidesEvaluator;
import org.drools.core.base.ValueType;
import org.drools.core.base.evaluators.CoincidesEvaluatorDefinition;
import org.drools.core.base.evaluators.TimeIntervalParser;
import org.drools.core.spi.Evaluator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hborjaille on 9/8/16.
 */
public class SCENECoincidesEvaluatorDefinition extends CoincidesEvaluatorDefinition {

    private Map<String, SCENECoincidesEvaluator> cache     = Collections.emptyMap();

    @Override
    public Evaluator getEvaluator(final ValueType type,
                                  final String operatorId,
                                  final boolean isNegated,
                                  final String parameterText,
                                  final Target left,
                                  final Target right) {
        if ( this.cache == Collections.EMPTY_MAP ) {
            this.cache = new HashMap<String, SCENECoincidesEvaluator>();
        }
        String key = left + ":" + right + ":" + isNegated + ":" + parameterText;
        SCENECoincidesEvaluator eval = this.cache.get( key );
        if ( eval == null ) {
            long[] params = TimeIntervalParser.parse( parameterText );
            eval = new SCENECoincidesEvaluator( type,
                    isNegated,
                    params,
                    parameterText,
                    left == Target.FACT,
                    right == Target.FACT );
            this.cache.put( key,
                    eval );
        }
        return eval;
    }
}