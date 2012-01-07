package backtype.support;

import cascading.flow.Flow;
import cascading.flow.hadoop.HadoopFlowConnector;
import cascading.operation.Identity;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tap.Tap;
import cascading.tuple.Fields;



public class CascadingUtils {
    public static void identityFlow(Tap source, Tap sink, Fields selectFields) {
        Pipe pipe = new Pipe("pipe");
        pipe = new Each(pipe, selectFields, new Identity());
        
        Flow flow = new HadoopFlowConnector().connect(source, sink, pipe);
        flow.complete();
    }

    public static boolean isSinkOf(Tap tap, Flow flow) {
        for(Object t: flow.getSinksCollection()) {
            if(t==tap) return true;
        }
        return false;
    }
}
