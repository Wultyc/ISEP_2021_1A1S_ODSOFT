package pt.isep.cms.batches.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import com.google.gwt.i18n.client.Constants;
import pt.isep.cms.batches.client.BatchesController;
import pt.isep.cms.batches.client.BatchesService;
import pt.isep.cms.batches.client.BatchesServiceAsync;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import pt.isep.cms.client.ContentWidget;
import pt.isep.cms.client.ShowcaseConstants;

/**
 * Main Content Widget for Batches.
 */
public class CwBatches extends ContentWidget {

	/**
	 * The constants used in this Content Widget.
	 */
	// This is only for generation, so we disable it
	public static interface CwConstants extends Constants {

		String cwBatchesDescription();

		String cwBatchesName();
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	/**
	 * Constructor.
	 *
	 * @param constants
	 *            the constants
	 */
	public CwBatches(ShowcaseConstants constants) {
		super(constants.cwBatchesName(), constants.cwBatchesDescription());
		this.globalConstants = constants;
		this.constants = constants;
	}

	/**
	 * Initialize this example.
	 */
	@Override
	public Widget onInitialize() {
		// The service should be created on GWT module loading
		BatchesServiceAsync rpcService = GWT.create(BatchesService.class);

		// Should setup the Presenter Panel for the Batches....
		VerticalPanel vPanel = new VerticalPanel();

		HandlerManager eventBus = new HandlerManager(null);
		BatchesController appViewer = new BatchesController(rpcService, eventBus, this.globalConstants);
		appViewer.go(vPanel);

		// Return the panel
		return vPanel;
	}
}
