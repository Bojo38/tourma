<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : roster.xsl
    Created on : 29 octobre 2012, 15:05
    Author     : WFMJ7631
    Description:
        Purpose of transformation follows.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Roster Type</title>
            </head>
            <body bgcolor="#E0E0F0">
                <div align="center">
                    <h2>
                        <xsl:value-of select="rostertype/name"/>
                    </h2>
                </div>
                <div align="left">
                    <h4>Reroll cost: 
                        <xsl:value-of select="rostertype/reroll"/>
                    </h4>
                    <h4>Can have apothecary ?: 
                        <xsl:value-of select="rostertype/apothecary"/>
                    </h4>
                </div>
                <h4>
                <table border="1" CELLPADDING="0" CELLSPACING="0">
                    <tr>
                        <th>Limit</th>
                        <th>Position</th>
                        <th>Movement</th>
                        <th>Strength</th>
                        <th>Agility</th>
                        <th>Armor</th>
                        <th>Skill</th>
                        <th>Cost</th>
                        <th>Single</th>
                        <th>Double</th>
                    </tr>
                    <xsl:for-each select="rostertype/playertypes/playerType">
                        <tr align="center">
                            <td> 
                                0-<xsl:value-of select="limit" />
                            </td>
                            <td>
                                <xsl:value-of select="position"/>
                            </td>
                            <td>
                                <xsl:value-of select="movement"/>
                            </td>
                            <td>
                                <xsl:value-of select="strength"/>
                            </td>
                            <td>
                                <xsl:value-of select="agility"/>
                            </td>
                            <td>
                                <xsl:value-of select="armor"/>
                            </td>
                            <td>
                                <xsl:value-of select="skills"/>
                            </td>
                            <td>
                                <xsl:value-of select="cost"/>
                            </td>
                            <td>
                                <xsl:value-of select="single"/>
                            </td>
                            <td>
                                <xsl:value-of select="double"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
                </h4>
                <!--<xsl:apply-templates/>-->
                <div align="left">
                    <h3>Inducements</h3>
                    <h4>Halfling Chef cost: 
                        <xsl:value-of select="rostertype/chef"/>
                    </h4>
                    <h4>Can have an Igor ?: 
                        <xsl:value-of select="rostertype/igor"/>
                    </h4>
                    <h4>Bribe the ref cost : 
                        <xsl:value-of select="rostertype/bribe"/>
                    </h4>
                    <h4>Can use local apothecary ?: 
                        <xsl:value-of select="rostertype/apothecary"/>
                    </h4>
                </div>
            </body>
        </html>
    </xsl:template>
    

</xsl:stylesheet>
