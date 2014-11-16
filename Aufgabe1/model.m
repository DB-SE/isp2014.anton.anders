beDone : Base [Eingabe] Bearbeiten* Ansehen+ Speicher :: _beDone ;

Base : [Tag] Text :: _Base ;

Bearbeiten : Löschen
	| erledigt ;

Ansehen : live+ :: live_
	| export+ :: export_ ;

live : GUI
	| WEB
	| Konsole ;

export : HTML
	| Markdown
	| txt ;

txt : [csv] :: _txt ;

Speicher : SQLDB
	| TXT ;

SQLDB : sqlite
	| externMySQL ;

