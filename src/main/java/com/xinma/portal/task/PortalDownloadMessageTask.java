package com.xinma.portal.task;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinma.base.datastore.enums.PortalDownloadMessageType;
import com.xinma.base.datastore.ext.message.MessageService;
import com.xinma.base.datastore.model.message.ProductTO;
import com.xinma.base.datastore.model.message.QueueMessageTO;
import com.xinma.portal.cache.product.ProductCache;
import com.xinma.portal.config.AliyunAccessConfig;
import com.xinma.portal.log.PortalCustomException;
import com.xinma.portal.log.error.PortalTaskError;

public class PortalDownloadMessageTask implements Runnable {

	@Autowired
	private MessageService messageService;

	@Autowired
	private ProductCache productCache;

	@Autowired
	private AliyunAccessConfig aliyunAccessConfig;

	private static Logger logger = LoggerFactory.getLogger(PortalDownloadMessageTask.class);

	@Override
	public void run() {

		ObjectMapper mapper = new ObjectMapper();
		do {
			try {
				QueueMessageTO queueMessageTO = messageService
						.popMessage(aliyunAccessConfig.getBackEndToPortalQueueName(), false, 2);
				if (queueMessageTO == null) {
					break;
				} else if (StringUtils.isBlank(queueMessageTO.getMessageBody())) {
					// 可能会有空消息
					continue;
				}
				// TODO
				if (queueMessageTO.getMessageType() == PortalDownloadMessageType.ProductTO.getValue().intValue()) {
					ProductTO product = mapper.readValue(queueMessageTO.getMessageBody(), ProductTO.class);
					productCache.saveProductToRedis(product);
				}

				// 删除消息
				messageService.deleteMessage(aliyunAccessConfig.getBackEndToPortalQueueName(),
						queueMessageTO.getMessageId());
			} catch (Throwable e) {
				logger.error("catch exception in PortalDownloadMessageTask.",
						new PortalCustomException(e, PortalTaskError.PortalDownloadMessageTask));
			}
		} while (true);

	}

}
