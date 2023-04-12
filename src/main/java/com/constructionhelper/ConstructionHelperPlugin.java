package com.constructionhelper;

import com.google.common.collect.ImmutableList;
import com.google.inject.Provides;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.worldhopper.WorldHopperConfig;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;


@Slf4j
@PluginDescriptor(
	name = "Construction Helper",
	description = "A plugin to help track materials needed for Construction",
	tags = {"construction", "helper", "materials", "tracking"}
)
public class ConstructionHelperPlugin extends Plugin
{
	private static final ImmutableList<String> BEFORE_OPTIONS = ImmutableList.of("Build");

	@Inject
	private Client client;

	@Inject
	private ClientToolbar clientToolbar;

	private NavigationButton navButton;

	@Inject
	private ConstructionHelperConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Construction Helper started!");

		log.info(ConstructionHelperConfig.class.getName());

		BufferedImage icon = new BufferedImage(48,48,BufferedImage.TYPE_INT_RGB);

		ConstructionHelperPanel panel = new ConstructionHelperPanel();

		navButton = NavigationButton.builder()
				.tooltip("Construction Helper")
				.icon(icon)
				.priority(10)
				.panel(panel)
				.build();

		if (config.showSidebar()) {
			clientToolbar.addNavigation(navButton);
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Construction Helper stopped!");
		clientToolbar.removeNavigation(navButton);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{

		}
	}

	@Provides
	ConstructionHelperConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ConstructionHelperConfig.class);
	}


	@Subscribe
	public void onConfigChanged(final ConfigChanged event)
	{
		if (event.getGroup().equals(ConstructionHelperConfig.GROUP))
		{
			switch (event.getKey())
			{
				case "showSidebar":
					if (config.showSidebar())
					{
						clientToolbar.addNavigation(navButton);
					}
					else
					{
						clientToolbar.removeNavigation(navButton);
					}
					break;

			}
		}
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{

		String option = event.getOption();
		String target = event.getTarget();
		int identifier = event.getIdentifier();
		int type = event.getType();

		if (type == 57 && identifier == 1) {

			client.createMenuEntry(-2)
					.setOption("Add To List")
					.setTarget(event.getTarget())
					.setType(MenuAction.RUNELITE)
					.onClick(e -> {
						client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Added to list", null);
						client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", target, null);
					});



		}







	}
}
