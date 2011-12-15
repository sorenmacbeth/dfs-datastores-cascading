package backtype.cascading.tap;

import cascading.flow.hadoop.HadoopFlowProcess;
import cascading.scheme.Scheme;
import cascading.scheme.SinkCall;
import cascading.scheme.SourceCall;
import cascading.tap.Tap;
import cascading.tap.TapException;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.SequenceFileInputFormat;

import java.io.IOException;


public class RawSequenceFile
    extends Scheme<HadoopFlowProcess, JobConf, RecordReader, OutputCollector, Object[], Void> {
    public RawSequenceFile(String keyField, String valueField) {
        super(new Fields(keyField, valueField));
    }

    @Override
    public void sourceConfInit(HadoopFlowProcess hadoopFlowProcess, Tap tap, JobConf entries) {
        entries.setInputFormat(SequenceFileInputFormat.class);
    }

    @Override
    public void sinkConfInit(HadoopFlowProcess hadoopFlowProcess, Tap tap, JobConf entries) {
        throw new TapException("cannot use as a sink.");
    }

    @Override
    public void sourcePrepare(HadoopFlowProcess flowProcess,
        SourceCall<Object[], RecordReader> sourceCall) {
        sourceCall.setContext(new Object[2]);

        sourceCall.getContext()[0] = sourceCall.getInput().createKey();
        sourceCall.getContext()[1] = sourceCall.getInput().createValue();
    }

    @Override public boolean source(HadoopFlowProcess hadoopFlowProcess,
        SourceCall<Object[], RecordReader> sourceCall) throws IOException {

        Comparable key = (Comparable) sourceCall.getContext()[0];
        Comparable value = (Comparable) sourceCall.getContext()[1];

        boolean result = sourceCall.getInput().next(key, value);

        if (!result) { return false; }

        Tuple ret = new Tuple();
        ret.add(key);
        ret.add(value);

        sourceCall.getIncomingEntry().setTuple(ret);
        return true;
    }

    @Override public void sink(HadoopFlowProcess hadoopFlowProcess,
        SinkCall<Void, OutputCollector> voidOutputCollectorSinkCall) throws IOException {
        throw new UnsupportedOperationException("Not supported.");
    }
}
