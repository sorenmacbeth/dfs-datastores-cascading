package backtype.cascading.tap;

import cascading.flow.FlowProcess;
import cascading.flow.hadoop.HadoopFlowProcess;
import cascading.scheme.Scheme;
import cascading.scheme.SinkCall;
import cascading.scheme.SourceCall;
import cascading.tap.Tap;
import cascading.tuple.*;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.lib.NullOutputFormat;

import java.io.IOException;
import org.apache.hadoop.mapred.RecordReader;


public class NullTap extends Tap  {
    
    public static class NullScheme extends Scheme<HadoopFlowProcess, JobConf, RecordReader, OutputCollector, Object[], Object[]> {
        public NullScheme() {
            super(Fields.ALL);
        }

        @Override
        public void sourceConfInit(HadoopFlowProcess prcs, Tap tap, JobConf config) {
            throw new IllegalArgumentException("Cannot use as a source");
        }

        @Override
        public void sinkConfInit(HadoopFlowProcess prcs, Tap tap, JobConf conf) {
            conf.setOutputFormat(NullOutputFormat.class);
        }

        @Override
        public boolean source(HadoopFlowProcess prcs, SourceCall<Object[], RecordReader> sc) throws IOException {
            throw new IllegalArgumentException("cannot source");
        }

        @Override
        public void sink(HadoopFlowProcess prcs, SinkCall<Object[], OutputCollector> sourceCall) throws IOException {
        }
    }

    public NullTap() {
        super(new NullScheme());
    }

    @Override
    public String getIdentifier() {
        return "NULLTAP";
    }

    @Override
    public TupleEntryIterator openForRead(FlowProcess prcs, Object input) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean createResource(Object config) throws IOException {
        return true;
    }

    @Override
    public boolean deleteResource(Object config) throws IOException {
        return true;
    }

    @Override
    public boolean resourceExists(Object config) throws IOException {
        return false;
    }    
    
    @Override
    public long getModifiedTime(Object config) throws IOException {
        return System.currentTimeMillis();
    }

    @Override
    public boolean equals(Object object) {
        return this==object;
    }
}
