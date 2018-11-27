**Class Diagrams**

The following diagram represents the data model for TourMa. It is abit complicated, then some sub-diagrams are available further.

```puml
class Tournament {
}

class Parameters
class Criteria
class Clan
class Category
class Coach
Class CoachMatch
Class Competitor
Class Group
Class GroupPoints
interface IContainsCoachs
interface ITournament
interface IWithNameAndPicture
interface IXMLExport
Class CoachMatch
Class ObjectAnnexRanking
Class ObjectRanking
Class Pool
Class RosterType
Class Round
Class Substitute
Class Team
Class TeamMatch
Class Value

Parameters "1" --* "1" Tournament
Criteria "n" --* "1" Parameters

TeamMatch --|> Match
CoachMatch --|> Match
CoachMatch "n" --* "1" TeamMatch

Match "n" --* "1" Round
Round "n" ---* "1" Tournament

Value "n" ---* "1" CoachMatch
Criteria "1" -o "1" Value

Competitor "n" --* "1" Tournament
Coach --|> Competitor
Team --|> Competitor
Coach "n" --* "1" Team

TeamMatch "n" --o "1" Team
CoachMatch "n" --o "1" Coach

Coach "2" --o "1" CoachMatch
Team "2" --o "1" TeamMatch

Clan "n" --* "1" Tournament
Coach "n" --o "1" Clan
Team "n" --o "1" Clan

RosterType "n" --* "1" Tournament

RosterType "1" --o "1" Coach

Group "n" --* "1" Tournament
RosterType "n" --o "1" Group

Category "n" --* "1" Tournament
Competitor "n" --o "m" Category

Pool "n" --* "1" Tournament
Competitor "n" --o "m" Pool
```
