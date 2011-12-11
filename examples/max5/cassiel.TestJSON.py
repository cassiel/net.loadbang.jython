import minjson

PATCHER = '''
{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 32.0, 114.0, 820.0, 522.0 ],
		"bglocked" : 0,
		"defrect" : [ 32.0, 114.0, 820.0, 522.0 ],
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
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 255.0, 30.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"id" : "obj-8"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "b 2",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"numoutlets" : 2,
					"patching_rect" : [ 165.0, 30.0, 32.5, 18.0 ],
					"outlettype" : [ "bang", "bang" ],
					"id" : "obj-5",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "script cassiel.TrustButVerify",
					"fontsize" : 10.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 165.0, 405.0, 134.0, 16.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-4",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "mxj net.loadbang.jython.mxj.ScriptEngine @script cassiel.TrustButVerify",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"numoutlets" : 2,
					"patching_rect" : [ 60.0, 450.0, 330.0, 18.0 ],
					"outlettype" : [ "", "" ],
					"id" : "obj-7",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 60.0, 30.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"id" : "obj-3"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "textedit",
					"text" : "----------begin_max5_patcher----------\r478.3ocyV00SBCCE84seEMM9lfY8iACiu3uCiwrwpPMasj0RD0v+c6taCACB\rMvf3Ks4d6s8dNmcus6qv.bldkvfQ2idBED7UXP.3p1QPqc.tLc0zhTCDFVsr\rLSTgGzrzhT6z4R0rWpDSsMGCMJ9tnAH5XXJNBLbinma2idosPXserPzrArTY\rwCP3rT0L7lnj4P5zYuMbRW1dUqrpzRXe3GqjoEauhQ9IrBg5RYiaGXkJWx.n\rS5vbkvHT1TqTq1B2D1X.pifon1gM3wcTM.GNKZsy0gg0CC7T4xVZsZ0wUNNo\rSxNlv8mJVB9PBvtTgbBTQId2kltjXEq.FfKzo4kBiAML9.zjDk.UF75wwiNN\rM2KEG0uEEWLMwUssPnxQFg8Pe5oS.cf.Si4mnnv+2IJddaAIJputsfcI0.Z+\rUXbKhb7BBRBTJvn2E6itrO8f1O5A8R2nHeEcijfd.EgryEpZCOpa37lxG5I1\rwPtVcLPxwER0uetEHWs+ckLidY0zNn2hTzOrKWXrRE7F1VwLYmXlKyyEvxcX\rtTluP6JTZg.54894yWDk3AhXWUDM5eGhXdfHxUEQ79WinrD39oX3OWXLFzLt\riURSCJgWGXswYxBpGrfeU0Ue5OomAhbFqC+FjFypGB\r-----------end_max5_patcher-----------\r",
					"linecount" : 13,
					"fontsize" : 14.0,
					"numinlets" : 1,
					"numoutlets" : 3,
					"patching_rect" : [ 60.0, 75.0, 745.0, 303.0 ],
					"outlettype" : [ "", "int", "" ],
					"id" : "obj-1",
					"fontname" : "Courier"
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-5", 0 ],
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
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-3", 0 ],
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
					"source" : [ "obj-1", 0 ],
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
 ]
	}

}
'''

def bang():
    x = minjson.read(PATCHER)
    print x

