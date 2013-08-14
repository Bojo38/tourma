-- Creator:       MySQL Workbench 5.2.47/ExportSQLite plugin 2009.12.02
-- Author:        WFMJ7631
-- Caption:       New Model
-- Project:       Name of the project
-- Changed:       2013-02-20 18:26
-- Created:       2013-02-19 16:09
PRAGMA foreign_keys = OFF;

-- Schema: tournament
BEGIN;
CREATE TABLE "Parameters"(
  "idParameters" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "GroupeEnabled" BOOL,
  "Organizer" TEXT,
  "Place" TEXT,
  "TournamentName" TEXT,
  "Date" INTEGER,
  "ByTeam" BOOL
);
CREATE TABLE "ClanParam"(
  "idClan" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "ClanEnable" BOOL DEFAULT FALSE,
  "TeamMatesNumber" INTEGER DEFAULT 3,
  "AvoidFirstMatch" BOOL DEFAULT TRUE,
  "AvoidMatch" BOOL DEFAULT FALSE,
  "Parameters_idParameters" INTEGER NOT NULL,
  CONSTRAINT "fk_Clan_Parameters1"
    FOREIGN KEY("Parameters_idParameters")
    REFERENCES "Parameters"("idParameters")
);
CREATE INDEX "ClanParam.fk_Clan_Parameters1_idx" ON "ClanParam"("Parameters_idParameters");
CREATE TABLE "IndivRanking"(
  "idIndivRanking" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Rank1" INTEGER DEFAULT 1,
  "Rank2" INTEGER DEFAULT 2,
  "Rank3" INTEGER DEFAULT 3,
  "Rank4" INTEGER DEFAULT 4,
  "Rank5" INTEGER DEFAULT 5,
  "Parameters_idParameters" INTEGER NOT NULL,
  "VictoryPoints" INTEGER DEFAULT 1000,
  "LargeVictoryPoints" INTEGER DEFAULT 1000,
  "Drawn" INTEGER DEFAULT 450,
  "Loss" INTEGER DEFAULT 0,
  "LittleLoss" INTEGER DEFAULT 0,
  "LargeVictoryGap" INTEGER DEFAULT 3,
  "LittleLossGap" INTEGER DEFAULT 1,
  CONSTRAINT "fk_IndivRanking_Parameters1"
    FOREIGN KEY("Parameters_idParameters")
    REFERENCES "Parameters"("idParameters")
);
CREATE INDEX "IndivRanking.fk_IndivRanking_Parameters1_idx" ON "IndivRanking"("Parameters_idParameters");
CREATE TABLE "TeamRanking"(
  "idTeamRanking" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Rank1" INTEGER DEFAULT 1,
  "Rank2" INTEGER DEFAULT 2,
  "Rank3" INTEGER DEFAULT 3,
  "Rank4" INTEGER DEFAULT 4,
  "Rank5" INTEGER DEFAULT 5,
  "Parameters_idParameters" INTEGER NOT NULL,
  "VictoryPoints" INTEGER DEFAULT 10,
  "DrawPoints" INTEGER DEFAULT 5,
  "LossPoints" INTEGER DEFAULT 0,
  "VictoryBonusPoints" INTEGER DEFAULT 10,
  "DrawBonusPoints" INTEGER DEFAULT 5,
  "TeamMatesNumber" INTEGER DEFAULT 1,
  "TeamPairing" INTEGER DEFAULT 1,
  "IndivPairing" INTEGER DEFAULT 0,
  "TeamVictory" BOOL DEFAULT FALSE,
  "Substitutes" BOOL DEFAULT FALSE,
  CONSTRAINT "fk_TeamRanking_Parameters1"
    FOREIGN KEY("Parameters_idParameters")
    REFERENCES "Parameters"("idParameters")
);
CREATE INDEX "TeamRanking.fk_TeamRanking_Parameters1_idx" ON "TeamRanking"("Parameters_idParameters");
CREATE TABLE "Group"(
  "idGroup" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Name" TEXT
);
CREATE TABLE "Clan"(
  "idClan" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Name" TEXT DEFAULT 'Aucun'
);
CREATE TABLE "Round"(
  "idRound" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Index" INTEGER,
  "Parent_idRound" INTEGER NOT NULL,
  CONSTRAINT "fk_Round_Round1"
    FOREIGN KEY("Parent_idRound")
    REFERENCES "Round"("idRound")
);
CREATE INDEX "Round.fk_Round_Round1_idx" ON "Round"("Parent_idRound");
CREATE TABLE "LRB"(
  "idLRB" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Name" TEXT DEFAULT 'LRB6',
  "AllowSpecialSkill" BOOL DEFAULT FALSE
);
CREATE TABLE "MainRanking"(
  "idMainRanking" INTEGER PRIMARY KEY NOT NULL,
  "Round_idRound" INTEGER NOT NULL,
  "RankingType" INTEGER,
--   INDIVIDUAL=1
--   TEAM=2
--   CLAN=3
--   GROUP=4
  CONSTRAINT "fk_MainRanking_Round1"
    FOREIGN KEY("Round_idRound")
    REFERENCES "Round"("idRound")
);
CREATE INDEX "MainRanking.fk_MainRanking_Round1_idx" ON "MainRanking"("Round_idRound");
CREATE TABLE "MainClanRank"(
  "idMainClanRank" INTEGER PRIMARY KEY NOT NULL,
  "Value1" INTEGER,
  "Value2" INTEGER,
  "Value3" INTEGER,
  "Value4" INTEGER,
  "Value5" INTEGER,
  "MainRanking_idMainRanking" INTEGER NOT NULL,
  "Clan_idClan" INTEGER NOT NULL,
  CONSTRAINT "fk_MainClanRank_MainRanking1"
    FOREIGN KEY("MainRanking_idMainRanking")
    REFERENCES "MainRanking"("idMainRanking"),
  CONSTRAINT "fk_MainClanRank_Clan1"
    FOREIGN KEY("Clan_idClan")
    REFERENCES "Clan"("idClan")
);
CREATE INDEX "MainClanRank.fk_MainClanRank_MainRanking1_idx" ON "MainClanRank"("MainRanking_idMainRanking");
CREATE INDEX "MainClanRank.fk_MainClanRank_Clan1_idx" ON "MainClanRank"("Clan_idClan");
CREATE TABLE "Criteria"(
  "idCriteria" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "IndivPointsFor" INTEGER DEFAULT 1,
  "IndivPointsAgainst" INTEGER DEFAULT 0,
  "TeamPointsFor" INTEGER DEFAULT 1,
  "TeamPointsAgainst" INTEGER DEFAULT 0,
  "Parameters_idParameters" INTEGER NOT NULL,
  CONSTRAINT "fk_Criteria_Parameters"
    FOREIGN KEY("Parameters_idParameters")
    REFERENCES "Parameters"("idParameters")
);
CREATE INDEX "Criteria.fk_Criteria_Parameters_idx" ON "Criteria"("Parameters_idParameters");
CREATE TABLE "RosterType"(
  "idRosterType" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Name" TEXT,
  "RosterTypecol" VARCHAR(45),
  "Group_idGroup" INTEGER NOT NULL,
  "LRB_idLRB" INTEGER NOT NULL,
  "ExtraRerollCost" INTEGER DEFAULT 100000,
  "BabeCost" INTEGER DEFAULT 50000,
  "WizardCost" INTEGER DEFAULT 150000,
  "LocalApothecaryCost" INTEGER DEFAULT 100000,
  "IgorCost" INTEGER DEFAULT 100000,
  "AssistantCost" INTEGER DEFAULT 10000,
  "CheerleaderCost" INTEGER DEFAULT 10000,
  "ApothecaryCost" INTEGER DEFAULT 50000,
  "RerollCost" INTEGER,
  "Apothecary" BOOL,
  "BribeCost" INTEGER,
  "ChefCost" INTEGER,
  "Igor" BOOL,
  "Image" BLOB,
  CONSTRAINT "fk_RosterType_Group1"
    FOREIGN KEY("Group_idGroup")
    REFERENCES "Group"("idGroup"),
  CONSTRAINT "fk_RosterType_LRB1"
    FOREIGN KEY("LRB_idLRB")
    REFERENCES "LRB"("idLRB")
);
CREATE INDEX "RosterType.fk_RosterType_Group1_idx" ON "RosterType"("Group_idGroup");
CREATE INDEX "RosterType.fk_RosterType_LRB1_idx" ON "RosterType"("LRB_idLRB");
CREATE TABLE "PlayerType"(
  "idPlayerType" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Position" TEXT,
  "RosterType_idRosterType" INTEGER NOT NULL,
  "Limit" INTEGER,
  "Movement" INTEGER,
  "Strength" INTEGER,
  "Agility" INTEGER,
  "Armor" INTEGER,
  "Cost" INTEGER,
  CONSTRAINT "fk_PlayerType_RosterType1"
    FOREIGN KEY("RosterType_idRosterType")
    REFERENCES "RosterType"("idRosterType")
);
CREATE INDEX "PlayerType.fk_PlayerType_RosterType1_idx" ON "PlayerType"("RosterType_idRosterType");
CREATE TABLE "SkillType"(
  "idSkillType" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Name" TEXT,
  "LRB_idLRB" INTEGER NOT NULL,
  "Accronym" TEXT,
  "Special" BOOL DEFAULT false,
  CONSTRAINT "fk_SkillType_LRB1"
    FOREIGN KEY("LRB_idLRB")
    REFERENCES "LRB"("idLRB")
);
CREATE INDEX "SkillType.fk_SkillType_LRB1_idx" ON "SkillType"("LRB_idLRB");
CREATE TABLE "PlayerTypeSkillType"(
  "idPlayerTypeSkillType" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "SkillType_idSkillType" INTEGER NOT NULL,
  "PlayerType_idPlayerType" INTEGER NOT NULL,
  "AssociationType" INTEGER,
--   Simple: 1
--   Double: 2
--   Special: 3
  CONSTRAINT "fk_PlayerTypeSkillType_SkillType1"
    FOREIGN KEY("SkillType_idSkillType")
    REFERENCES "SkillType"("idSkillType"),
  CONSTRAINT "fk_PlayerTypeSkillType_PlayerType1"
    FOREIGN KEY("PlayerType_idPlayerType")
    REFERENCES "PlayerType"("idPlayerType")
);
CREATE INDEX "PlayerTypeSkillType.fk_PlayerTypeSkillType_SkillType1_idx" ON "PlayerTypeSkillType"("SkillType_idSkillType");
CREATE INDEX "PlayerTypeSkillType.fk_PlayerTypeSkillType_PlayerType1_idx" ON "PlayerTypeSkillType"("PlayerType_idPlayerType");
CREATE TABLE "StarPlayer"(
  "idStarPlayer" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "LRB_idLRB" INTEGER NOT NULL,
  "Name" TEXT,
  "Position" TEXT,
  "Movement" INTEGER,
  "Strength" INTEGER,
  "Agility" INTEGER,
  "Armor" INTEGER,
  "Cost" INTEGER,
  CONSTRAINT "fk_StarPlayer_LRB1"
    FOREIGN KEY("LRB_idLRB")
    REFERENCES "LRB"("idLRB")
);
CREATE INDEX "StarPlayer.fk_StarPlayer_LRB1_idx" ON "StarPlayer"("LRB_idLRB");
CREATE TABLE "StarRosterType"(
  "idStarRosterType" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "RosterType_idRosterType" INTEGER NOT NULL,
  "StarPlayer_idStarPlayer" INTEGER NOT NULL,
  CONSTRAINT "fk_StarRosterType_RosterType1"
    FOREIGN KEY("RosterType_idRosterType")
    REFERENCES "RosterType"("idRosterType"),
  CONSTRAINT "fk_StarRosterType_StarPlayer1"
    FOREIGN KEY("StarPlayer_idStarPlayer")
    REFERENCES "StarPlayer"("idStarPlayer")
);
CREATE INDEX "StarRosterType.fk_StarRosterType_RosterType1_idx" ON "StarRosterType"("RosterType_idRosterType");
CREATE INDEX "StarRosterType.fk_StarRosterType_StarPlayer1_idx" ON "StarRosterType"("StarPlayer_idStarPlayer");
CREATE TABLE "AnnexRanking"(
  "idAnnexTeamRanking" INTEGER PRIMARY KEY NOT NULL,
  "Criteria_idCriteria" INTEGER NOT NULL,
  "Round_idRound" INTEGER NOT NULL,
  "RankingType" INTEGER,
--   INDIVIDUAL=1
--   TEAM=2
--   CLAN=3
--   GROUP=4
  CONSTRAINT "fk_AnnexTeamRanking_Criteria1"
    FOREIGN KEY("Criteria_idCriteria")
    REFERENCES "Criteria"("idCriteria"),
  CONSTRAINT "fk_AnnexTeamRanking_Round1"
    FOREIGN KEY("Round_idRound")
    REFERENCES "Round"("idRound")
);
CREATE INDEX "AnnexRanking.fk_AnnexTeamRanking_Criteria1_idx" ON "AnnexRanking"("Criteria_idCriteria");
CREATE INDEX "AnnexRanking.fk_AnnexTeamRanking_Round1_idx" ON "AnnexRanking"("Round_idRound");
CREATE TABLE "AnnexClanRank"(
  "idAnnexClanRanking" INTEGER PRIMARY KEY NOT NULL,
  "AnnexRanking_idAnnexTeamRanking" INTEGER NOT NULL,
  "Clan_idClan" INTEGER NOT NULL,
  CONSTRAINT "fk_AnnexClanRank_AnnexRanking1"
    FOREIGN KEY("AnnexRanking_idAnnexTeamRanking")
    REFERENCES "AnnexRanking"("idAnnexTeamRanking"),
  CONSTRAINT "fk_AnnexClanRank_Clan1"
    FOREIGN KEY("Clan_idClan")
    REFERENCES "Clan"("idClan")
);
CREATE INDEX "AnnexClanRank.fk_AnnexClanRank_AnnexRanking1_idx" ON "AnnexClanRank"("AnnexRanking_idAnnexTeamRanking");
CREATE INDEX "AnnexClanRank.fk_AnnexClanRank_Clan1_idx" ON "AnnexClanRank"("Clan_idClan");
CREATE TABLE "AnnexGroupRank"(
  "idAnnexGroupRank" INTEGER PRIMARY KEY NOT NULL,
  "ValueFor" INTEGER,
  "ValueAgainst" INTEGER,
  "AnnexRanking_idAnnexTeamRanking" INTEGER NOT NULL,
  "Group_idGroup" INTEGER NOT NULL,
  CONSTRAINT "fk_AnnexGroupRank_AnnexRanking1"
    FOREIGN KEY("AnnexRanking_idAnnexTeamRanking")
    REFERENCES "AnnexRanking"("idAnnexTeamRanking"),
  CONSTRAINT "fk_AnnexGroupRank_Group1"
    FOREIGN KEY("Group_idGroup")
    REFERENCES "Group"("idGroup")
);
CREATE INDEX "AnnexGroupRank.fk_AnnexGroupRank_AnnexRanking1_idx" ON "AnnexGroupRank"("AnnexRanking_idAnnexTeamRanking");
CREATE INDEX "AnnexGroupRank.fk_AnnexGroupRank_Group1_idx" ON "AnnexGroupRank"("Group_idGroup");
CREATE TABLE "Roster"(
  "idRoster" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Name" TEXT,
  "Rostercol" VARCHAR(45),
  "RosterType_idRosterType" INTEGER NOT NULL,
  "PlayerType_idPlayerType" INTEGER NOT NULL,
  "Reroll" INTEGER,
  "Apothecary" BOOL,
  "FanFactor" INTEGER,
  "Cheerleaders" INTEGER,
  "Assistants" INTEGER,
  "ExtraReroll" INTEGER,
  "LocalApothecary" INTEGER,
  "Babes" INTEGER,
  "Bribe" INTEGER,
  "Chef" BOOL,
  "Igor" BOOL,
  "Wizard" BOOL,
  CONSTRAINT "fk_Roster_RosterType1"
    FOREIGN KEY("RosterType_idRosterType")
    REFERENCES "RosterType"("idRosterType"),
  CONSTRAINT "fk_Roster_PlayerType1"
    FOREIGN KEY("PlayerType_idPlayerType")
    REFERENCES "PlayerType"("idPlayerType")
);
CREATE INDEX "Roster.fk_Roster_RosterType1_idx" ON "Roster"("RosterType_idRosterType");
CREATE INDEX "Roster.fk_Roster_PlayerType1_idx" ON "Roster"("PlayerType_idPlayerType");
CREATE TABLE "Player"(
  "idPlayer" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Roster_idRoster" INTEGER NOT NULL,
  "PlayerType_idPlayerType" INTEGER NOT NULL,
  "Name" TEXT,
  "Playercol" VARCHAR(45),
  CONSTRAINT "fk_Player_Roster1"
    FOREIGN KEY("Roster_idRoster")
    REFERENCES "Roster"("idRoster"),
  CONSTRAINT "fk_Player_PlayerType1"
    FOREIGN KEY("PlayerType_idPlayerType")
    REFERENCES "PlayerType"("idPlayerType")
);
CREATE INDEX "Player.fk_Player_Roster1_idx" ON "Player"("Roster_idRoster");
CREATE INDEX "Player.fk_Player_PlayerType1_idx" ON "Player"("PlayerType_idPlayerType");
CREATE TABLE "Skill"(
  "idSkill" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Name" TEXT,
  "SkillType_idSkillType" INTEGER NOT NULL,
  "SingleSkillCost" INTEGER,
  "DoubleSkillCost" INTEGER,
  "StrengthSkillCost" INTEGER,
  "AgilitySkillCost" INTEGER,
  "ArmorSkillCost" INTEGER,
  "MovementSkillCost" INTEGER,
  CONSTRAINT "fk_Skill_SkillType1"
    FOREIGN KEY("SkillType_idSkillType")
    REFERENCES "SkillType"("idSkillType")
);
CREATE INDEX "Skill.fk_Skill_SkillType1_idx" ON "Skill"("SkillType_idSkillType");
CREATE TABLE "PlayerSkill"(
  "idPlayerSkill" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "PlayerSkillcol" VARCHAR(45),
  "Player_idPlayer" INTEGER NOT NULL,
  "Skill_idSkill" INTEGER NOT NULL,
  CONSTRAINT "fk_PlayerSkill_Player1"
    FOREIGN KEY("Player_idPlayer")
    REFERENCES "Player"("idPlayer"),
  CONSTRAINT "fk_PlayerSkill_Skill1"
    FOREIGN KEY("Skill_idSkill")
    REFERENCES "Skill"("idSkill")
);
CREATE INDEX "PlayerSkill.fk_PlayerSkill_Player1_idx" ON "PlayerSkill"("Player_idPlayer");
CREATE INDEX "PlayerSkill.fk_PlayerSkill_Skill1_idx" ON "PlayerSkill"("Skill_idSkill");
CREATE TABLE "PlayerTypeSkill"(
  "idPlayerTypeSkill" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "PlayerType_idPlayerType" INTEGER NOT NULL,
  "Skill_idSkill" INTEGER NOT NULL,
  CONSTRAINT "fk_PlayerTypeSkill_PlayerType1"
    FOREIGN KEY("PlayerType_idPlayerType")
    REFERENCES "PlayerType"("idPlayerType"),
  CONSTRAINT "fk_PlayerTypeSkill_Skill1"
    FOREIGN KEY("Skill_idSkill")
    REFERENCES "Skill"("idSkill")
);
CREATE INDEX "PlayerTypeSkill.fk_PlayerTypeSkill_PlayerType1_idx" ON "PlayerTypeSkill"("PlayerType_idPlayerType");
CREATE INDEX "PlayerTypeSkill.fk_PlayerTypeSkill_Skill1_idx" ON "PlayerTypeSkill"("Skill_idSkill");
CREATE TABLE "StarRoster"(
  "idStarRoster" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "StarRostercol" INTEGER,
  "StarPlayer_idStarPlayer" INTEGER NOT NULL,
  "Roster_idRoster" INTEGER NOT NULL,
  "Roster_PlayerType_idPlayerType" INTEGER NOT NULL,
  CONSTRAINT "fk_StarRoster_StarPlayer1"
    FOREIGN KEY("StarPlayer_idStarPlayer")
    REFERENCES "StarPlayer"("idStarPlayer"),
  CONSTRAINT "fk_StarRoster_Roster1"
    FOREIGN KEY("Roster_idRoster","Roster_PlayerType_idPlayerType")
    REFERENCES "Roster"("idRoster","PlayerType_idPlayerType")
);
CREATE INDEX "StarRoster.fk_StarRoster_StarPlayer1_idx" ON "StarRoster"("StarPlayer_idStarPlayer");
CREATE INDEX "StarRoster.fk_StarRoster_Roster1_idx" ON "StarRoster"("Roster_idRoster","Roster_PlayerType_idPlayerType");
CREATE TABLE "StarSkill"(
  "idStarSkill" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "StarPlayer_idStarPlayer" INTEGER NOT NULL,
  "Skill_idSkill" INTEGER NOT NULL,
  CONSTRAINT "fk_StarSkill_StarPlayer1"
    FOREIGN KEY("StarPlayer_idStarPlayer")
    REFERENCES "StarPlayer"("idStarPlayer"),
  CONSTRAINT "fk_StarSkill_Skill1"
    FOREIGN KEY("Skill_idSkill")
    REFERENCES "Skill"("idSkill")
);
CREATE INDEX "StarSkill.fk_StarSkill_StarPlayer1_idx" ON "StarSkill"("StarPlayer_idStarPlayer");
CREATE INDEX "StarSkill.fk_StarSkill_Skill1_idx" ON "StarSkill"("Skill_idSkill");
CREATE TABLE StringConstants.CS_COACH(
  "idCoach" INTEGER PRIMARY KEY NOT NULL,
  "Name" TEXT,
  "Clan_idClan" INTEGER NOT NULL,
  "NafId" INTEGER DEFAULT 0,
  "Rank" INTEGER DEFAULT 110,
  "Roster_idRoster" INTEGER NOT NULL,
  CONSTRAINT "fk_Coach_Clan1"
    FOREIGN KEY("Clan_idClan")
    REFERENCES "Clan"("idClan"),
  CONSTRAINT "fk_Coach_Roster1"
    FOREIGN KEY("Roster_idRoster")
    REFERENCES "Roster"("idRoster")
);
CREATE INDEX "Coach.fk_Coach_Clan1_idx" ON StringConstants.CS_COACH("Clan_idClan");
CREATE INDEX "Coach.fk_Coach_Roster1_idx" ON StringConstants.CS_COACH("Roster_idRoster");
CREATE TABLE "Team"(
  "idTeam" INTEGER PRIMARY KEY NOT NULL,
  "Name" TEXT,
  "Coach_idCoach" INTEGER NOT NULL,
  CONSTRAINT "fk_Team_Coach1"
    FOREIGN KEY("Coach_idCoach")
    REFERENCES StringConstants.CS_COACH("idCoach")
);
CREATE INDEX "Team.fk_Team_Coach1_idx" ON "Team"("Coach_idCoach");
CREATE TABLE "Match"(
  "idMatch" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Coach_idCoach2" INTEGER NOT NULL,
  "Coach_idCoach1" INTEGER NOT NULL,
  "Round_idRound" INTEGER NOT NULL,
  "Roster_idRoster2" INTEGER NOT NULL,
  "Roster_idRoster1" INTEGER NOT NULL,
  CONSTRAINT "fk_Match_Coach1"
    FOREIGN KEY("Coach_idCoach2")
    REFERENCES StringConstants.CS_COACH("idCoach"),
  CONSTRAINT "fk_Match_Coach2"
    FOREIGN KEY("Coach_idCoach1")
    REFERENCES StringConstants.CS_COACH("idCoach"),
  CONSTRAINT "fk_Match_Round1"
    FOREIGN KEY("Round_idRound")
    REFERENCES "Round"("idRound"),
  CONSTRAINT "fk_Match_Roster1"
    FOREIGN KEY("Roster_idRoster2")
    REFERENCES "Roster"("idRoster"),
  CONSTRAINT "fk_Match_Roster2"
    FOREIGN KEY("Roster_idRoster1")
    REFERENCES "Roster"("idRoster")
);
CREATE INDEX "Match.fk_Match_Coach1_idx" ON "Match"("Coach_idCoach2");
CREATE INDEX "Match.fk_Match_Coach2_idx" ON "Match"("Coach_idCoach1");
CREATE INDEX "Match.fk_Match_Round1_idx" ON "Match"("Round_idRound");
CREATE INDEX "Match.fk_Match_Roster1_idx" ON "Match"("Roster_idRoster2");
CREATE INDEX "Match.fk_Match_Roster2_idx" ON "Match"("Roster_idRoster1");
CREATE TABLE "Value"(
  "idValue" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Value1" INTEGER,
  "Value2" INTEGER,
  "Match_idMatch" INTEGER NOT NULL,
  "Criteria_idCriteria" INTEGER NOT NULL,
  CONSTRAINT "fk_Value_Match1"
    FOREIGN KEY("Match_idMatch")
    REFERENCES "Match"("idMatch"),
  CONSTRAINT "fk_Value_Criteria1"
    FOREIGN KEY("Criteria_idCriteria")
    REFERENCES "Criteria"("idCriteria")
);
CREATE INDEX "Value.fk_Value_Match1_idx" ON "Value"("Match_idMatch");
CREATE INDEX "Value.fk_Value_Criteria1_idx" ON "Value"("Criteria_idCriteria");
CREATE TABLE "AnnexIndivRank"(
  "idAnnexIndivRank" INTEGER PRIMARY KEY NOT NULL,
  "ValueFor" INTEGER,
  "ValueAgainst" INTEGER,
  "Coach_idCoach" INTEGER NOT NULL,
  "AnnexRanking_idAnnexTeamRanking" INTEGER NOT NULL,
  CONSTRAINT "fk_AnnexIndivRank_Coach1"
    FOREIGN KEY("Coach_idCoach")
    REFERENCES StringConstants.CS_COACH("idCoach"),
  CONSTRAINT "fk_AnnexIndivRank_AnnexRanking1"
    FOREIGN KEY("AnnexRanking_idAnnexTeamRanking")
    REFERENCES "AnnexRanking"("idAnnexTeamRanking")
);
CREATE INDEX "AnnexIndivRank.fk_AnnexIndivRank_Coach1_idx" ON "AnnexIndivRank"("Coach_idCoach");
CREATE INDEX "AnnexIndivRank.fk_AnnexIndivRank_AnnexRanking1_idx" ON "AnnexIndivRank"("AnnexRanking_idAnnexTeamRanking");
CREATE TABLE "AnnexTeamRank"(
  "idAnnexTeamRank" INTEGER PRIMARY KEY NOT NULL,
  "ValueFor" INTEGER,
  "ValueAgainst" INTEGER,
  "AnnexTeamRanking_idAnnexTeamRanking" INTEGER NOT NULL,
  "Team_idTeam" INTEGER NOT NULL,
  CONSTRAINT "fk_AnnexTeamRank_AnnexTeamRanking1"
    FOREIGN KEY("AnnexTeamRanking_idAnnexTeamRanking")
    REFERENCES "AnnexRanking"("idAnnexTeamRanking"),
  CONSTRAINT "fk_AnnexTeamRank_Team1"
    FOREIGN KEY("Team_idTeam")
    REFERENCES "Team"("idTeam")
);
CREATE INDEX "AnnexTeamRank.fk_AnnexTeamRank_AnnexTeamRanking1_idx" ON "AnnexTeamRank"("AnnexTeamRanking_idAnnexTeamRanking");
CREATE INDEX "AnnexTeamRank.fk_AnnexTeamRank_Team1_idx" ON "AnnexTeamRank"("Team_idTeam");
CREATE TABLE "MainTeamRank"(
  "idMainTeamRank" INTEGER PRIMARY KEY NOT NULL,
  "Value1" INTEGER,
  "Value2" INTEGER,
  "Value3" INTEGER,
  "Value4" INTEGER,
  "Value5" INTEGER,
  "MainRanking_idMainRanking" INTEGER NOT NULL,
  "Team_idTeam" INTEGER NOT NULL,
  CONSTRAINT "fk_MainTeamRank_MainRanking1"
    FOREIGN KEY("MainRanking_idMainRanking")
    REFERENCES "MainRanking"("idMainRanking"),
  CONSTRAINT "fk_MainTeamRank_Team1"
    FOREIGN KEY("Team_idTeam")
    REFERENCES "Team"("idTeam")
);
CREATE INDEX "MainTeamRank.fk_MainTeamRank_MainRanking1_idx" ON "MainTeamRank"("MainRanking_idMainRanking");
CREATE INDEX "MainTeamRank.fk_MainTeamRank_Team1_idx" ON "MainTeamRank"("Team_idTeam");
CREATE TABLE "MainIndivRank"(
  "idMainIndivRank" INTEGER PRIMARY KEY NOT NULL,
  "Value1" INTEGER,
  "Value2" INTEGER,
  "Value3" INTEGER,
  "Value4" INTEGER,
  "Value5" INTEGER,
  "MainRanking_idMainRanking" INTEGER NOT NULL,
  "Coach_idCoach" INTEGER NOT NULL,
  CONSTRAINT "fk_MainIndivRank_MainRanking1"
    FOREIGN KEY("MainRanking_idMainRanking")
    REFERENCES "MainRanking"("idMainRanking"),
  CONSTRAINT "fk_MainIndivRank_Coach1"
    FOREIGN KEY("Coach_idCoach")
    REFERENCES StringConstants.CS_COACH("idCoach")
);
CREATE INDEX "MainIndivRank.fk_MainIndivRank_MainRanking1_idx" ON "MainIndivRank"("MainRanking_idMainRanking");
CREATE INDEX "MainIndivRank.fk_MainIndivRank_Coach1_idx" ON "MainIndivRank"("Coach_idCoach");
CREATE TABLE "MainGroupRank"(
  "idMainGroupRank" INTEGER PRIMARY KEY NOT NULL,
  "Value1" INTEGER,
  "Value2" INTEGER,
  "Value3" INTEGER,
  "Value4" INTEGER,
  "Value5" INTEGER,
  "MainRanking_idMainRanking" INTEGER NOT NULL,
  "Coach_idCoach" INTEGER NOT NULL,
  "Group_idGroup" INTEGER NOT NULL,
  CONSTRAINT "fk_MainGroupRank_MainRanking1"
    FOREIGN KEY("MainRanking_idMainRanking")
    REFERENCES "MainRanking"("idMainRanking"),
  CONSTRAINT "fk_MainGroupRank_Coach1"
    FOREIGN KEY("Coach_idCoach")
    REFERENCES StringConstants.CS_COACH("idCoach"),
  CONSTRAINT "fk_MainGroupRank_Group1"
    FOREIGN KEY("Group_idGroup")
    REFERENCES "Group"("idGroup")
);
CREATE INDEX "MainGroupRank.fk_MainGroupRank_MainRanking1_idx" ON "MainGroupRank"("MainRanking_idMainRanking");
CREATE INDEX "MainGroupRank.fk_MainGroupRank_Coach1_idx" ON "MainGroupRank"("Coach_idCoach");
CREATE INDEX "MainGroupRank.fk_MainGroupRank_Group1_idx" ON "MainGroupRank"("Group_idGroup");
COMMIT;
