{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 101.0, 247.0, 652.0, 330.0 ],
		"bglocked" : 0,
		"defrect" : [ 101.0, 247.0, 652.0, 330.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 0,
		"default_fontsize" : 10.0,
		"default_fontface" : 0,
		"default_fontname" : "Courier",
		"gridonopen" : 0,
		"gridsize" : [ 15.0, 5.0 ],
		"gridsnaponopen" : 0,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"metadata" : [  ],
		"boxes" : [ 			{
				"box" : 				{
					"maxclass" : "tab",
					"presentation_rect" : [ 30.0, 15.0, 585.0, 285.0 ],
					"fontsize" : 10.0,
					"numinlets" : 1,
					"id" : "obj-14",
					"patching_rect" : [ 30.0, 15.0, 209.0, 89.0 ],
					"numoutlets" : 3,
					"presentation" : 1,
					"tabs" : [ "Void", "AllLights", "SimpleCounter", "MultiCounter", "MultiCounterSKETCH" ],
					"outlettype" : [ "int", "", "" ],
					"fontname" : "Courier"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend run",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"id" : "obj-3",
					"patching_rect" : [ 150.0, 200.0, 85.0, 16.0 ],
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Courier"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "placeholder monome",
					"fontsize" : 10.0,
					"numinlets" : 2,
					"id" : "obj-4",
					"patching_rect" : [ 389.0, 212.0, 132.0, 14.0 ],
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Courier"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "toggle",
					"numinlets" : 1,
					"id" : "obj-5",
					"patching_rect" : [ 75.0, 135.0, 30.0, 30.0 ],
					"numoutlets" : 1,
					"outlettype" : [ "int" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend press",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"id" : "obj-6",
					"patching_rect" : [ 300.0, 150.0, 99.0, 16.0 ],
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Courier"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route /128/press",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"id" : "obj-7",
					"patching_rect" : [ 300.0, 130.0, 107.0, 16.0 ],
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"fontname" : "Courier"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "udpreceive 8000",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"id" : "obj-8",
					"patching_rect" : [ 300.0, 110.0, 110.0, 16.0 ],
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Courier",
					"color" : [ 0.945098, 0.913725, 0.407843, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "mxj net.loadbang.jython.mxj.ScriptEngine @placeholder monome",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"id" : "obj-9",
					"patching_rect" : [ 150.0, 240.0, 410.0, 16.0 ],
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"fontname" : "Courier"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"id" : "obj-10",
					"patching_rect" : [ 75.0, 195.0, 30.0, 30.0 ],
					"numoutlets" : 1,
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "metro 10",
					"fontsize" : 10.0,
					"numinlets" : 2,
					"id" : "obj-11",
					"patching_rect" : [ 75.0, 175.0, 65.0, 16.0 ],
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"fontname" : "Courier"
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 1 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [ 309.5, 198.0, 246.0, 198.0, 246.0, 225.0, 159.5, 225.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [ 84.5, 234.0, 159.5, 234.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [ 398.5, 232.0, 159.5, 232.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 0 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ]
	}

}
