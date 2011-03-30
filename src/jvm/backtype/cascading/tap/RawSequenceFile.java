package backtype.cascading.tap;

import cascading.scheme.Scheme;

import cascading.tap.Tap;
import cascading.tap.TapException;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;
import java.io.IOException;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.SequenceFileInputFormat;


public class RawSequenceFile extends Scheme
  {
  public RawSequenceFile(String keyField, String valueField)
    {
    super(new Fields(keyField, valueField));
    }

  @Override
  public void sourceInit( Tap tap, JobConf conf )
    {
    conf.setInputFormat( SequenceFileInputFormat.class );
    }

  @Override
  public void sinkInit( Tap tap, JobConf conf )
    {
        throw new TapException("cannot use as a sink");
    }

  @Override
  public Tuple source( Object key, Object value )
    {
      Tuple ret = new Tuple();
      ret.add((Comparable) key);
      ret.add((Comparable)value);
      return ret;
    }

    @Override
    public void sink(TupleEntry te, OutputCollector oc) throws IOException {
        throw new TapException("cannot use as a sink");
    }

  }
