package backtype.cascading.tap;

import cascading.scheme.Scheme;
import cascading.tap.Tap;
import cascading.tuple.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.lib.NullOutputFormat;

import java.io.IOException;


public class NullTap extends Tap {

    public static class NullScheme extends Scheme {

        public NullScheme() {
            super(Fields.ALL);
        }

        @Override
        public void sourceInit(Tap tap, JobConf conf) throws IOException {
            throw new IllegalArgumentException("Cannot use as a source");
        }

        @Override
        public void sinkInit(Tap tap, JobConf conf) throws IOException {
            conf.setOutputFormat(NullOutputFormat.class);
        }

        @Override
        public Tuple source(Object k, Object v) {
            throw new IllegalArgumentException("cannot source");
        }

        @Override
        public void sink(TupleEntry tuple, OutputCollector output) throws IOException {
            output.collect(NullWritable.get(), NullWritable.get());
        }

    }

    protected String getCategory(Comparable obj) {
        return "";
    }

    public NullTap() {
        super(new NullScheme());
    }

    @Override
    public boolean deletePath(JobConf conf) throws IOException {
        return true;
    }

    @Override
    public Path getPath() {
        return new Path("/dev/null");
    }

    @Override
    public TupleEntryIterator openForRead(JobConf jc) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TupleEntryCollector openForWrite(JobConf jc) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean makeDirs(JobConf jc) throws IOException {
        return true;
    }

    @Override
    public boolean pathExists(JobConf jc) throws IOException {
        return false;
    }

    @Override
    public long getPathModified(JobConf jc) throws IOException {
        return System.currentTimeMillis();
    }

    @Override
    public boolean equals(Object object) {
        return this == object;
    }
}
