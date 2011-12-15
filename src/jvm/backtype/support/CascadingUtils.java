package backtype.support;

import cascading.flow.Flow;
import cascading.flow.hadoop.HadoopFlowConnector;
import cascading.operation.Identity;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tap.Tap;
import cascading.tuple.Fields;
import org.apache.hadoop.mapred.JobConf;

import java.util.Map.Entry;

public class CascadingUtils {
    public static void identityFlow(Tap source, Tap sink, Fields selectFields) {
        Pipe pipe = new Pipe("pipe");
        pipe = new Each(pipe, selectFields, new Identity());
        new HadoopFlowConnector().connect(source, sink, pipe).complete();
    }

    public static boolean isSinkOf(Tap tap, Flow<JobConf> flow) {
        for (Entry<String, Tap> e : flow.getSinks().entrySet()) {
            if (e.getValue() == tap) { return true; }
        }
        return false;
    }
}
