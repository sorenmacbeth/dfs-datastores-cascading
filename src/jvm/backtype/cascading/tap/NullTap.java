package backtype.cascading.tap;

import cascading.flow.FlowProcess;
import cascading.flow.hadoop.HadoopFlowProcess;
import cascading.scheme.Scheme;
import cascading.scheme.SinkCall;
import cascading.scheme.SourceCall;
import cascading.tap.Tap;
import cascading.tuple.Fields;
import cascading.tuple.TupleEntryCollector;
import cascading.tuple.TupleEntryIterator;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.lib.NullOutputFormat;

import java.io.IOException;


public class NullTap extends Tap {

    public static class NullScheme extends
        Scheme<HadoopFlowProcess, JobConf, RecordReader, OutputCollector<NullWritable, NullWritable>, Object[], Void> {

        public NullScheme() {
            super(Fields.ALL);
        }

        @Override
        public void sourceConfInit(HadoopFlowProcess hadoopFlowProcess, Tap tap, JobConf entries) {
            throw new IllegalArgumentException("Cannot use as a source");
        }

        @Override
        public void sinkConfInit(HadoopFlowProcess hadoopFlowProcess, Tap tap, JobConf entries) {
            entries.setOutputFormat(NullOutputFormat.class);
        }

        @Override public boolean source(HadoopFlowProcess hadoopFlowProcess,
            SourceCall<Object[], RecordReader> recordReaderSourceCall) throws IOException {
            throw new IllegalArgumentException("Can't source.");
        }

        @Override public void sink(HadoopFlowProcess hadoopFlowProcess,
            SinkCall<Void, OutputCollector<NullWritable, NullWritable>> voidOutputCollectorSinkCall)
            throws IOException {
            voidOutputCollectorSinkCall.getOutput().collect(NullWritable.get(), NullWritable.get());
        }

    }

    public NullTap() {
        super(new NullScheme());
    }

    @Override
    public boolean equals(Object object) {
        return object.getClass() == this.getClass() && this == object;
    }

    @Override public String getIdentifier() {
        return "/dev/null";
    }

    @Override public TupleEntryIterator openForRead(FlowProcess flowProcess, Object o)
        throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override public TupleEntryCollector openForWrite(FlowProcess flowProcess, Object o)
        throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override public boolean createResource(Object o) throws IOException {
        return true;
    }

    @Override public boolean deleteResource(Object o) throws IOException {
        return true;
    }

    @Override public boolean resourceExists(Object o) throws IOException {
        return false;
    }

    @Override public long getModifiedTime(Object o) throws IOException {
        return System.currentTimeMillis();
    }
}
