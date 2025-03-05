package com.adaptris.triggered.test;

import com.adaptris.core.*;
import com.adaptris.core.runtime.AdapterRegistryMBean;
import com.adaptris.core.runtime.ChannelManagerMBean;
import com.adaptris.core.runtime.WorkflowManagerMBean;
import com.adaptris.core.triggered.JmxChannelTriggerMBean;
import com.adaptris.testing.SingleAdapterFunctionalTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.management.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

public class DefaultFunctionalTest  extends SingleAdapterFunctionalTest {
    Path messagesOut;

    @BeforeAll
    @Override
    public void setup() throws Exception {
        super.setup();
        messagesOut = Paths.get("./messages-out");
        Files.deleteIfExists(messagesOut);
    }

    @Test
    public void test() throws MalformedObjectNameException, IOException, CoreException, TimeoutException, InstanceNotFoundException {
        ObjectName channelName = adapterManagerMBean.getChildren().stream().findFirst().get();
        ChannelManagerMBean channel1 = JMX.newMBeanProxy(this.jmxConnector.getMBeanServerConnection(), channelName, ChannelManagerMBean.class);

        ObjectName workflowName = channel1.getChildren().stream().filter(on -> on.getCanonicalName().contains("id=eager-nobel")).findFirst().get();
        WorkflowManagerMBean workflow1 = JMX.newMBeanProxy(this.jmxConnector.getMBeanServerConnection(), workflowName, WorkflowManagerMBean.class);
        assertEquals(ClosedState.getInstance().toString(), workflow1.getComponentState().toString());

        ObjectInstance triggerName = this.jmxConnector.getMBeanServerConnection().getObjectInstance(ObjectName.getInstance("Adaptris:type=TriggeredChannel, uid=jmx-trigger"));
        JmxChannelTriggerMBean triggerMBean = JMX.newMBeanProxy(this.jmxConnector.getMBeanServerConnection(), triggerName.getObjectName(), JmxChannelTriggerMBean.class);
        assertNotNull(triggerMBean);

        triggerMBean.trigger();

        assertTrue(Files.exists(messagesOut));
        assertEquals(Files.list(messagesOut).count(), 1);


    }
}
