package micdoodle8.mods.galacticraft.core.blocks;

import micdoodle8.mods.galacticraft.core.entities.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.core.entities.GCCoreEntitySpaceship;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * Copyright 2012-2013, micdoodle8
 *
 *  All rights reserved.
 *
 */
public class GCCoreBlockLandingPad extends Block
{
	public GCCoreBlockLandingPad(int i)
	{
		super(i, Material.iron);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
	}

    @Override
    public void func_94332_a(IconRegister par1IconRegister)
    {
    	this.field_94336_cN = par1IconRegister.func_94245_a("galacticraftcore:launch_pad");
    }

    @Override
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
    {
    	final int id = GCCoreBlocks.landingPad.blockID;

		if (par1World.getBlockId(par2 + 1, par3, par4) == id && par1World.getBlockId(par2 + 2, par3, par4) == id && par1World.getBlockId(par2 + 3, par3, par4) == id)
		{
			return false;
		}

		if (par1World.getBlockId(par2 - 1, par3, par4) == id && par1World.getBlockId(par2 - 2, par3, par4) == id && par1World.getBlockId(par2 - 3, par3, par4) == id)
		{
			return false;
		}

		if (par1World.getBlockId(par2, par3, par4 + 1) == id && par1World.getBlockId(par2, par3, par4 + 2) == id && par1World.getBlockId(par2, par3, par4 + 3) == id)
		{
			return false;
		}

		if (par1World.getBlockId(par2, par3, par4 - 1) == id && par1World.getBlockId(par2, par3, par4 - 2) == id && par1World.getBlockId(par2, par3, par4 - 3) == id)
		{
			return false;
		}

    	if (par1World.getBlockId(par2, par3 - 1, par4) == GCCoreBlocks.landingPad.blockID && par5 == 1)
    	{
    		return false;
    	}
    	else
    	{
            return this.canPlaceBlockAt(par1World, par2, par3, par4);
    	}
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
    	for (Object o : par1World.getEntitiesWithinAABB(GCCoreEntitySpaceship.class, AxisAlignedBB.getAABBPool().getAABB(par2 - 2, par3 - 2, par4 - 2, par2 + 2, par3 + 2, par4 + 2)))
    	{
    		Entity e = (Entity) o;
    		
    		if (e instanceof EntitySpaceshipBase)
    		{
    			((EntitySpaceshipBase) e).checkValidLaunchPadBroken();
    		}
    	}
    	
    	super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
}
