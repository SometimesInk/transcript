package com.ink;

import com.ink.event.ChatEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Transcript.MODID, version = Transcript.VERSION)
public class Transcript
{
    public static final String MODID = "transcript";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Register events
        MinecraftForge.EVENT_BUS.register(new ChatEvent());
    }
}