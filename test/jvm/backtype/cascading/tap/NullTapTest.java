package backtype.cascading.tap;

import backtype.support.FSTestCase;
import cascading.flow.FlowConnector;
import cascading.operation.Identity;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.scheme.TextLine;
import cascading.tap.Lfs;
import cascading.tap.Tap;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntryCollector;
import org.apache.hadoop.mapred.JobConf;
import static backtype.support.TestUtils.*;


public class NullTapTest extends FSTestCase {

    public void testNullTap() throws Exception {
        String tmp = getTmpPath(fs, "pail");
        Tap source = new Lfs(new TextLine(new Fields("line")), tmp);
        TupleEntryCollector coll = source.openForWrite(new JobConf());
        coll.add(new Tuple("lalalala"));
        coll.add(new Tuple("lalalala2"));
        coll.close();

        Pipe p = new Pipe("pipe");
        p = new Each(p, new Fields("line"), new Identity());

        //just make sure there's not exception
        new FlowConnector().connect(source, new NullTap(), p).complete();

    }

}
