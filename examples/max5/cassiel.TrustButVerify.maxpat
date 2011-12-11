{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 30.0, 66.0, 820.0, 522.0 ],
		"bglocked" : 0,
		"defrect" : [ 30.0, 66.0, 820.0, 522.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 0,
		"default_fontsize" : 10.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 0,
		"gridsize" : [ 15.0, 15.0 ],
		"gridsnaponopen" : 0,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"metadata" : [  ],
		"boxes" : [ 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "fooble",
					"patching_rect" : [ 420.0, 405.0, 38.0, 16.0 ],
					"id" : "obj-6",
					"fontname" : "Arial",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 10.0,
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"patching_rect" : [ 255.0, 30.0, 20.0, 20.0 ],
					"id" : "obj-8",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "b 2",
					"patching_rect" : [ 165.0, 30.0, 32.5, 18.0 ],
					"id" : "obj-5",
					"fontname" : "Arial",
					"numinlets" : 1,
					"numoutlets" : 2,
					"fontsize" : 10.0,
					"outlettype" : [ "bang", "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "script cassiel.TrustButVerify",
					"patching_rect" : [ 165.0, 405.0, 134.0, 16.0 ],
					"id" : "obj-4",
					"fontname" : "Arial",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 10.0,
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "mxj net.loadbang.jython.mxj.ScriptEngine @script cassiel.TrustButVerify",
					"patching_rect" : [ 60.0, 450.0, 330.0, 18.0 ],
					"id" : "obj-7",
					"fontname" : "Arial",
					"numinlets" : 1,
					"numoutlets" : 2,
					"fontsize" : 10.0,
					"outlettype" : [ "", "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"patching_rect" : [ 60.0, 30.0, 20.0, 20.0 ],
					"id" : "obj-3",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "textedit",
					"text" : "----------begin_max5_patcher----------\r303.3ockR9rSCCCCF+bxSQT10BpoCM92IdAPhynITViYMn1jpFWVgo8tSSR6\r15.DrKNJe19yt+R2RI7U1NvwY2wdlQHaoDRPxKPFtS3Uxt7RoKTFuBbN4Zfm\rDygPGFz2jWZc6kqkXdg1r9kFHGi1OO8lKSSXhEW6OtJDE8RrkC8XZqrsXIfg\rAIFTe0ZPm9SHnk02TTNVI9QMDcmy26iuCirJjf+PiVVNtUZUPyt5sKlyOLUs\rYbnYdscTpOj7OAhA1za323AVncAJ.M+MTxRE9iE2Fu7qPI6bgRxYCFwOBlwW\rCm78Qum8n28IelL989vrm7QvnhJrkGQ0vxvK0lS+qKXhWeJpc11l7wQN7vwN\rfGE3PsQhZq4nZDSpoPqTPH8HmpzpZq1fCqvIKHcG8K.RNcKj\r-----------end_max5_patcher-----------\r",
					"linecount" : 9,
					"patching_rect" : [ 60.0, 75.0, 745.0, 303.0 ],
					"id" : "obj-1",
					"fontname" : "Courier",
					"numinlets" : 1,
					"numoutlets" : 3,
					"fontsize" : 14.0,
					"outlettype" : [ "", "int", "" ]
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 1 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ]
	}

}
