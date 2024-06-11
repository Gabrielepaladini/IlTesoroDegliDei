/*
                    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package di.uniba.it.iltesorodeglidei;

import di.uniba.it.map.b.adventure.database.Database;
import di.uniba.it.map.b.adventure.gui.EngineGUI;
import di.uniba.it.map.b.adventure.gui.Sound;
import di.uniba.it.map.b.adventure.parser.ParserOutput;
import di.uniba.it.map.b.adventure.type.AdvObject;
import di.uniba.it.map.b.adventure.type.AdvObjectContainer;
import di.uniba.it.map.b.adventure.type.Command;
import di.uniba.it.map.b.adventure.type.CommandType;

import di.uniba.it.map.b.adventure.type.Room;
import di.uniba.it.map.b.adventures.GameDescription;

import static di.uniba.it.map.b.adventures.Utils.readFile;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public class IlTesoroDegliDei extends GameDescription {
                            
    //variabile che permette di verificare se mattoneRotto è stato spostato o meno
    private static boolean mattonePushed = false;

    //variabile che permette di verificare se la statuaAtrio è stata spostata o meno
    private static boolean statuAtrioPushed = false;

    //variabile che permette di verificare se il primo pulsante in salaStatue in ordine corretto è premuto
    private static boolean pulsante1Pushed = false;

    //variabile che permette di verificare se il secondo pulsante in salaStatue in ordine corretto è premuto
    private static boolean pulsante2Pushed = false;

    //variabile che permette di verificare se il terzo pulsante in salaStatue in ordine corretto è premuto
    private static boolean pulsante3Pushed = false;

    //variabile utilizzata per regolamentare l'ordine con cui devono essere premuti i pulsanti in salaStatue
    private static int order = 0;

    //variabile che permette di verificare se l'utente sta visualizzando incisione futuro
    private static boolean incisioneFuturoExaminated = false;

    //variabile che permette di verificare se la pietraAlbero è stata premuta
    private static boolean pietraAlberoPushed = false;

    //variabile che permette di verificare se la pietraFiume è stata premuta
    private static boolean pietraFiumePushed = false;

    //variabile che permette di verificare se la pietraFiamma è stata premuta
    private static boolean pietraFiammaPushed = false;

    //variabile utilizzata per regolamentare l'ordine con cui devono essere premute le pietre in CameraSapienza
    private static int sequence = 0;

    //variabile che permette di stabilire se le pietre errate di CameraSapienza sono state premute
    private static boolean isPushed = false;

    //variabile che permette di verificare se l'utente sta visualizzando incisione passato
    private static boolean incisionePassatoExaminated = false;

    //variabile che permette di varificare se il sarcofagoLibro è stato premuto o meno
    private static boolean sarcofagoLibroPushed = false;

    //variabile che permette di varificare se il sarcofagoCerchio è stato premuto o meno
    private static boolean sarcofagoCerchioPushed = false;

    //variabile che permette di varificare se il sarcofagoCorona è stato premuto o meno
    private static boolean sarcofagoCoronaPushed = false;

    //variabile utilizzata per regolamentare l'ordine con cui devono essere premuti i sarcofagi in SalaCripta
    private static int number = 0;

    //variabile che permette di verificare se la fiaccola è stata raccolta o meno
    private static boolean torchtaken = false;

    //variabile che permette di verificare se la chiaveIngresso è stata raccolta o meno
    private static boolean keytaken = false;

    //variabile che permette di verificare se il simbolo in corridoioSimbolo è stato raccolto o meno
    private static boolean symboltaken = false;

    //variabile che permette di verificare se il biglietto in corridoioSimbolo è stato raccolto o meno
    private static boolean papertaken = false;

    @Override
    public void startGame() {
        //implementazione comandi di gioco
        Command nord = new Command(CommandType.NORTH, "nord");
        nord.setAlias(new String[]{"Nord", "NORD", "n"});
        getCommands().add(nord);

        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"Sud", "SUD", "s"});
        getCommands().add(sud);

        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"Est", "EST", "e"});
        getCommands().add(est);

        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"Ovest", "OVEST", "o"});
        getCommands().add(ovest);

        Command usa = new Command(CommandType.USE, "usa");
        usa.setAlias(new String[]{"utilizza", "impiega", "accendi", "scrivi"});
        getCommands().add(usa);

        Command premi = new Command(CommandType.PRESS, "premi");
        premi.setAlias(new String[]{"spingi", "schiaccia", "attiva", "sposta"});
        getCommands().add(premi);

        Command apri = new Command(CommandType.OPEN, "apri");
        apri.setAlias(new String[]{"spalanca"});
        getCommands().add(apri);

        Command guarda = new Command(CommandType.LOOK, "guarda");
        guarda.setAlias(new String[]{"osserva", "vedi", "descrivi"});
        getCommands().add(guarda);

        Command esamina = new Command(CommandType.EXAMINE, "esamina");
        esamina.setAlias(new String[]{"analizza", "studia", "ispeziona"});
        getCommands().add(esamina);

        Command raccogli = new Command(CommandType.PICK_UP, "raccogli");
        raccogli.setAlias(new String[]{"prendi", "afferra", "recupera"});
        getCommands().add(raccogli);

        Command inserisci = new Command(CommandType.INSERT, "inserisci");
        inserisci.setAlias(new String[]{"infila", "metti"});
        getCommands().add(inserisci);

        Command leggi = new Command(CommandType.READ, "leggi");
        getCommands().add(leggi);

        //implementazione stanze di gioco
        //ESTERNO
        Room Esterno = new Room(0, 0, "Esterno", "Ti trovi davanti alla maestosa piramide che contiene il tesoro degli Dei");

        //PRIMO PIANO  
        Room Ingresso = new Room(1, 0, "Ingresso", "Sono all'Ingresso della piramide, fa davvero freddo ed è tutto buio qui. \n"
                + "Dovrei trovare qualcosa con cui fare luce.");
        Ingresso.setLook("Si tratta di un lungo corridoio che termina con una porta, sarà sicuramente chiusa.\n"
                + "Vedo una fiaccola sulla parete destra magari posso prenderla per illuminare. \n"
                + "\nSulla parete sinistra c'è un mattone fuoriposto rispetto agli altri...");
        getRooms().add(Ingresso);
        Ingresso.setHint("Prova a SPOSTARE gli oggetti che vedi,\nper arrivare alla soluzione...");

        //inizializzo la stanza in cui il giocatore inizia il gioco
        setCurrentRoom(Ingresso);

        Room Atrio = new Room(2, 0, "Atrio", "Sono nell'atrio principale, è davvero enorme! \n");
        Atrio.setLook("C'è una grande statua davanti a me, fa davvero impressione! \n"
                + "Vedo due porte ai lati dovrei vedere cosa nascondono...");
        getRooms().add(Atrio);
        if (!Database.loadUsedObjects(3)) {
            Atrio.setLocked(true);
        } else {
            Atrio.setLocked(false);
        }

        Atrio.setHint("Forse gli oggetti che vedi potrebbero essere spostati...");

        Room SalaStatue = new Room(3, 0, "SalaStatue", "Un'enorme stanza con tre statue gigantesche.\nMI TERRORIZZANO!\n");
        SalaStatue.setLook("Sono circondato da antiche figure di pietra che sembrano muoversi.\n\n"
                + "Una di queste statue presenta un'antica incisione.\n\n"
                + "Ogni statua ha un simbolo sulla base: un Sole dorato, una luna argentata\ne una stella scintillante.\n\n"
                + "Alle loro spalle ci sono tre pulsanti con gli stessi simboli.\n"
                + "A nord e a sud due corridoi che sembrano accessibili\n"
                + "mentre ad est una porta molto piccola. Mi sembra sia chiusa.\n\n"
                + "Forse le camere circostanti ti saranno di aiuto...");
        getRooms().add(SalaStatue);
        if (Database.loadUsedObjects(7) && Database.loadUsedObjects(9)) {
            SalaStatue.setLocked(false);
        } else {
            SalaStatue.setLocked(true);
        }
        SalaStatue.setHint("Usa la torcia quando esamini le incisioni e attento a cosa rappresentano...");

        Room SalaCripta = new Room(4, 0, "SalaCripta", "Questa stanza è decorata con sarcofagi, raffigurazioni di antichi re\ne regine e simboli che non riesco a comprendere.\n");
        SalaCripta.setLook("Al centro c'è un'antica tavola genealogica incisa su pietra,\ncircodndata da sei diversi sarcofagi.\n\n"
                + "Ogni sarcofago presenta un simbolo diverso(corona, libro, arpa, stella, cerchio,\ncroce) che sembrano possano essere premuti...");
        getRooms().add(SalaCripta);
        if (Database.loadUsedObjects(15)) {
            SalaCripta.setLocked(false);
        } else {
            SalaCripta.setLocked(true);
        }
        SalaCripta.setHint("'SAGGEZZA', 'CICLO DELLA VITA', 'TESORO'");

        Room SalaIncisione = new Room(5, 0, "SalaIncisione", "Non si vede quasi nulla qui... \nIntravedo un corridoio stretto e forse una torcia per terra.\n");
        SalaIncisione.setLook("Sembra ci sia una torcia per terra, mi potrà sicuramente essere utile!\n"
                + "Alla fine del corridoio c'è una piccola stanza con una strana incisione.");
        getRooms().add(SalaIncisione);
        SalaIncisione.setHint("Non affidarti solo ai tuoi occhi, sfrutta quello che hai trovato...");

        Room CorridoioSimbolo = new Room(6, 0, "CorridoioSimbolo", "Uno strano corridoio molto lungo.\n");
        CorridoioSimbolo.setLook("Sulla sinistra vedo delle scale, non mi sembrano accessibili.\nPer terra un biglietto molto antico.\nIn fondo c'è una strana rappresentazione, chissà cosa indica...");
        getRooms().add(CorridoioSimbolo);
        CorridoioSimbolo.setHint("Vuoi davvero un aiuto qui? Ci sono solo due oggetti...");

        Room Scale1 = new Room(7, 1, "Scale1Piano", "Delle scale che portano verso il primo piano.\n");
        Scale1.setLook("Davvero?\nCosa aspetti che ti dica, saliamo!");
        getRooms().add(Scale1);
        if (Database.loadUsedObjects(20)) {
            Scale1.setLocked(false);
        } else {
            Scale1.setLocked(true);
        }

        Room CameraSapienza = new Room(8, 0, "CameraSapienza", "Una camera piena di strani manofatti e dipinti molto affascinanti.");
        CameraSapienza.setLook("Al centro della stanza c'è un antico libro \naperto su un piedistallo con una chiave dorata affianco.\n\n"
                + "Sulle pareti sei dipinti differenti: dipintoalbero, dipintofiume,\ndipintofiamma, dipintocolomba, dipintolibro, dipintostella.\n\n"
                + "Alla base di ciascun dipinto c'è un piedistallo con una pietra\ndifferente: pietraalabero, pietrafiume, pietrafiamma,\npietracolomba, pietralibro, pietrastella.\n\n"
                + "Credo possano essere premute, forse in un certo ordine...");
        getRooms().add(CameraSapienza);
        CameraSapienza.setHint("'ILLUMINA', 'RADICI', 'SCORRE'");

        Room CameraScrigno = new Room(9, 0, "CameraScrigno", "E' una camera davvero molto piccola e stretta.");
        CameraScrigno.setLook("C'è uno scrigno dorato davanti a me.");
        getRooms().add(CameraScrigno);
        if (Database.loadUsedObjects(35)) {
            CameraScrigno.setLocked(false);
        } else {
            CameraScrigno.setLocked(true);
        }
        CameraSapienza.setHint("Devi semplicemente usare la chiave che hai trovato.");

        Room Scale2 = new Room(10, 0, "Scale2Piano", "Delle scale che portano verso il secondo piano.");
        Scale2.setLook("Si...sai, sono le stesse che abbiamo visto prima.");
        getRooms().add(Scale2);
        Scale2.setLocked(true);
        Scale2.setHint("Stai davvero chiedendo un suggerimento per delle scale?");

        //SECONDO PIANO
        Room SalaRitualeAstrale = new Room(11, 1, "SalaRitualeAstrale", "È una stanza misteriosa!\nSono circondato da simboli astrali e cerchi magici incisi sulle pareti.\n");
        SalaRitualeAstrale.setLook("Al centro c'è un'antica tavoletta cerimoniale.\n"
                + "Intorno alla stanza ci sono sei cerchi con simboli astrali:\n- cerchioterra\n- cerchioplutone\n- cerchiosaturno\n- cerchiovenere\n- cerchiomarte\n- cerchiogiove"
                + "\nallineati lungo il pavimento,\ndevo solo capire come sono legati tra loro...");
        getRooms().add(SalaRitualeAstrale);

        Room SalaTesoro = new Room(12, 1, "SalaTesoro", "È una camera immensa!\nQui dentro c'è più oro di quanto ne abbia visto nella mia vita!\n");
        SalaTesoro.setLook("Vedo tre pilastri altissimi e con decorazioni differenti.\n"
                + "(primopilastro, secondopilastro, terzopilastro)\n\n"
                + "Al centro della stanza un imponente sarcofago. Al di sopra vedo una pergamena.\n"
                + "Forse conterrà qualcosa di prezioso.\n\n"
                + "In fondo vedo delle scale, mi sembrano bloccate...");
        getRooms().add(SalaTesoro);
        if (Database.loadUsedObjects(66)) {
            SalaTesoro.setLocked(false);
        } else {
            SalaTesoro.setLocked(true);
        }
        SalaTesoro.setHint("Solo uno dei tre è un valore positivo...");

        Room Scale3 = new Room(13, 1, "Scale3Piano", "Delle scale che portano verso il terzo piano.");
        Scale3.setLook("Già, sai...per salire si usano le scale.\n\nPensa...anche per scendere!");
        getRooms().add(Scale3);
        Scale3.setLocked(true);

        //TERZO PIANO
        Room SalaOsservatorio = new Room(14, 2, "SalaOsservatorio", "Una stanza all'apparenza magica!");
        SalaOsservatorio.setLook("C'è un antico manoscritto posato su un vecchio scrittoio.\n"
                + "Alla sua destra uno strano telescopio.");
        getRooms().add(SalaOsservatorio);

        //implementazione Mappa di gioco
        //ESTERNO
        Esterno.setPathMiniMap("/Img/Rooms/Mappa_esterno.png");
        Esterno.setPathView("/Img/view/Piramide_Esterno.png");
        Esterno.setLocked(true);

        //PRIMO PIANO
        Ingresso.setSouth(Esterno);
        Ingresso.setNorth(Atrio);
        Ingresso.setPathMiniMap("/Img/Rooms/Mappa1_Ingresso.png");
        if (!Database.loadTakenObjects(3)) {
            Ingresso.setPathView("/Img/view/Ingresso.png");
        } else {
            Ingresso.setPathView("/Img/view/PrendiChiave.png");
        }

        Atrio.setSouth(Ingresso);
        Atrio.setWest(CorridoioSimbolo);
        Atrio.setEast(SalaStatue);
        Atrio.setPathMiniMap("/Img/Rooms/Mappa1_Atrio.png");
        if (!Database.loadTakenObjects(7)) {
            Atrio.setPathView("/Img/view/Atrio.png");
        } else {
            Atrio.setPathView("/Img/view/SpostaStatuaPrendiSimbolo.png");
        }

        CorridoioSimbolo.setEast(Atrio);
        CorridoioSimbolo.setWest(Scale1);
        CorridoioSimbolo.setPathMiniMap("/Img/Rooms/Mappa1_CorridoioSimbolo.png");
        if (!Database.loadTakenObjects(8) && !Database.loadTakenObjects(9)) {
            CorridoioSimbolo.setPathView("/Img/view/CorridoioSimbolo.png");
        } else if (!Database.loadTakenObjects(8) && Database.loadTakenObjects(9)) {
            CorridoioSimbolo.setPathView("/Img/view/PrendiSimbolo.png");
        } else if (Database.loadTakenObjects(8) && !Database.loadTakenObjects(9)) {
            CorridoioSimbolo.setPathView("/Img/view/PrendiBiglietto.png");
        } else if (Database.loadTakenObjects(8) && Database.loadTakenObjects(9)) {
            CorridoioSimbolo.setPathView("/Img/view/PrendiSimboloPrendiBiglietto.png");
        }

        Scale1.setNorth(SalaRitualeAstrale);
        Scale1.setEast(CorridoioSimbolo);
        Scale1.setPathMiniMap("/Img/Rooms/Mappa1_Scale1.png");
        Scale1.setPathView("/Img/view/Scala1.png");

        SalaStatue.setWest(Atrio);
        SalaStatue.setNorth(CameraSapienza);
        SalaStatue.setSouth(SalaIncisione);
        SalaStatue.setEast(SalaCripta);
        SalaStatue.setPathMiniMap("/Img/Rooms/Mappa1_SalaStatue.png");
        SalaStatue.setPathView("/Img/view/SalaStatue.png");

        SalaIncisione.setNorth(SalaStatue);
        SalaIncisione.setPathMiniMap("/Img/Rooms/Mappa1_SalaIncisione.png");
        if (!Database.loadTakenObjects(17)) {
            SalaIncisione.setPathView("/Img/view/SalaIncisione.png");
        } else {
            SalaIncisione.setPathView("/Img/view/SalaIncisionePrendiTorcia.png");
        }

        SalaCripta.setWest(SalaStatue);
        SalaCripta.setPathMiniMap("/Img/Rooms/Mappa1_SalaCripta.png");
        SalaCripta.setPathView("/Img/view/CriptaDegliAntenati.png");

        CameraSapienza.setSouth(SalaStatue);
        CameraSapienza.setNorth(CameraScrigno);
        CameraSapienza.setPathMiniMap("/Img/Rooms/Mappa1_CameraSapienza.png");
        CameraSapienza.setPathView("/Img/view/CameraSapienza.png");

        CameraScrigno.setSouth(CameraSapienza);
        CameraScrigno.setPathMiniMap("/Img/Rooms/Mappa1_CameraScrigno.png");
        CameraScrigno.setPathView("/Img/view/CameraScrigno.png");

        //SECONDO PIANO
        SalaRitualeAstrale.setSouth(Scale1);
        SalaRitualeAstrale.setNorth(SalaTesoro);
        SalaRitualeAstrale.setPathMiniMap("/Img/Rooms/Mappa2_SalaRitualeAstrale.png");
        SalaRitualeAstrale.setPathView("/Img/view/SalaRitualeAstrale.png");

        SalaTesoro.setSouth(SalaRitualeAstrale);
        SalaTesoro.setNorth(Scale2);
        SalaTesoro.setPathMiniMap("/Img/Rooms/Mappa2_SalaTesoro.png");
        SalaTesoro.setPathView("/Img/view/SalaTesoro.png");

        Scale2.setSouth(SalaTesoro);
        Scale2.setNorth(SalaOsservatorio);
        Scale2.setPathMiniMap("/Img/Rooms/Mappa2_Scale3.png");
        Scale2.setPathView("/Img/view/Scala2.png");

        //TERZO PIANO
        SalaOsservatorio.setSouth(Scale2);
        SalaOsservatorio.setPathMiniMap("/Img/Rooms/Mappa3_Osservatorio.png");
        SalaOsservatorio.setPathView("/Img/view/SalaOsservatorio.png");

        //Implementazione oggetti di gioco
        //oggetti inventario        
        //oggetti PRIMO PIANO
        //Ingresso                
        AdvObject fiaccola = new AdvObject(1, "Fiaccola", "Una fiaccola! Sarà molto utile per vederci qualcosa.");
        fiaccola.setAlias(new String[]{"torcia", "luce", "fiaccola"});
        Ingresso.getObjects().add(fiaccola);
        fiaccola.setPickupable(true);
        fiaccola.setUsable(true);
        fiaccola.setLookable(true);
        getObjects().add(fiaccola);

        AdvObject mattoneRotto = new AdvObject(2, "Mattone", "L'unico mattone che sembra fuori posto rispetto agli altri...");
        mattoneRotto.setAlias(new String[]{"mattone"});
        Ingresso.getObjects().add(mattoneRotto);
        mattoneRotto.setPushable(true);
        if (Database.loadTakenObjects(3)) {
            mattonePushed = true;
        }
        getObjects().add(mattoneRotto);

        AdvObject chiaveIngresso = new AdvObject(3, "ChiaveIngresso", "Una chiave nascosta dietro al mattone, forse aprirà la porta lì in fondo.");
        chiaveIngresso.setAlias(new String[]{"chiave"});
        Ingresso.getObjects().add(chiaveIngresso);
        chiaveIngresso.setUsable(true);
        chiaveIngresso.setHidden(true);
        chiaveIngresso.setLookable(false);
        chiaveIngresso.setPickupable(false);
        getObjects().add(chiaveIngresso);

        AdvObject portaIngresso = new AdvObject(4, "PortaIngresso", "Una porta che mi impedisce di proseguire.");
        portaIngresso.setAlias(new String[]{"porta"});
        Ingresso.getObjects().add(portaIngresso);
        portaIngresso.setOpen(false);
        portaIngresso.setOpenable(true);
        portaIngresso.setLookable(true);

        //Atrio
        AdvObject statuaAtrio = new AdvObject(5, "StatuaAtrio", "Un'enorme statua di un dio egizio.\n"
                + "Quando il vento soffia qui dentro, sembra muoversi...");
        statuaAtrio.setAlias(new String[]{"statua"});
        Atrio.getObjects().add(statuaAtrio);
        statuaAtrio.setPushable(true);
        if (Database.loadTakenObjects(7)) {
            statuAtrioPushed = true;
        }
        getObjects().add(statuaAtrio);

        AdvObject portaAtrioDestra = new AdvObject(6, "PortaAtrioDestra", "La porta è chiusa!\n"
                + "Vedo uno strano marchingengo in cui posso inserire due oggeti di forma quadrata...\n\n"
                + "Mi fanno pensare a delle mattonelle...");
        Atrio.getObjects().add(portaAtrioDestra);
        portaAtrioDestra.setAlias(new String[]{"portadestra"});
        portaAtrioDestra.setOpen(false);
        portaAtrioDestra.setOpenable(true);
        portaAtrioDestra.setLookable(true);

        AdvObject portaAtrioSinistra = new AdvObject(66, "PortaAtrioSinistra", "La porta sembra aperta.");
        Atrio.getObjects().add(portaAtrioSinistra);
        portaAtrioSinistra.setAlias(new String[]{"portasinistra"});
        portaAtrioSinistra.setOpen(true);
        portaAtrioSinistra.setOpenable(true);
        portaAtrioSinistra.setLookable(true);

        AdvObject simbolo2 = new AdvObject(7, "Ankh", "Un altro di quegli strani simboli.\n"
                + "Credo che mi sarà utile per aprire quella porta!");
        simbolo2.setAlias(new String[]{"mattonella", "simbolo", "ank", "anhk", "ankh"});
        Atrio.getObjects().add(simbolo2);
        simbolo2.setLookable(true);
        simbolo2.setUsable(true);
        simbolo2.setHidden(true);
        simbolo2.setPickupable(false);
        getObjects().add(simbolo2);

        //CorridoioSimbolo
        AdvObject bigliettoSimbolo = new AdvObject(8, "Biglietto", "Un biglietto con incisioni antiche che posso tradurre.\n");
        bigliettoSimbolo.setText("Recita: 'Trova due simboli primordiali disposti in quest'area\nper avanzare nel tuo cammino..."
                + "usali uno per volta, ma nell'ordine corretto...");
        bigliettoSimbolo.setAlias(new String[]{"biglietto", "documento"});
        CorridoioSimbolo.getObjects().add(bigliettoSimbolo);
        Atrio.getObjects().add(bigliettoSimbolo);
        Ingresso.getObjects().add(bigliettoSimbolo);
        bigliettoSimbolo.setPickupable(true);
        bigliettoSimbolo.setLookable(true);
        bigliettoSimbolo.setTaken(false);
        bigliettoSimbolo.setReadable(true);
        getObjects().add(bigliettoSimbolo);

        AdvObject simbolo1 = new AdvObject(9, "Occhio di Horus", "Una mattonella quadrata con su inciso uno strano simbolo egizio.\n"
                + "Forse posso raccoglierla.");
        simbolo1.setAlias(new String[]{"mattonella", "simbolo", "occhio"});
        CorridoioSimbolo.getObjects().add(simbolo1);
        Atrio.getObjects().add(simbolo1);
        simbolo1.setPickupable(true);
        simbolo1.setUsable(true);
        simbolo1.setLookable(true);
        getObjects().add(simbolo1);

        //SalaStatue
        AdvObject statua1 = new AdvObject(10, "Statua1", "Sulla base di questa statua è inciso un sole dorato.\n"
                + "E' presente anche un'incisione.");
        statua1.setAlias(new String[]{"statuaSole"});
        SalaStatue.getObjects().add(statua1);
        statua1.setLookable(true);

        AdvObject statua2 = new AdvObject(11, "Statua2", "Sulla base di questa statua è inciso una luna argentea.\n"
                + "E' presente anche un'incisione dorata.");
        statua2.setAlias(new String[]{"statuaLuna", "statua"});
        SalaStatue.getObjects().add(statua2);
        statua2.setLookable(true);

        AdvObject statua3 = new AdvObject(12, "Statua3", "Sulla base di questa statua è inciso una stella brillante.\n"
                + "E' presente anche un'incisione dorata.");
        statua3.setAlias(new String[]{"statuaStella"});
        SalaStatue.getObjects().add(statua3);
        statua3.setLookable(true);

        AdvObject incisioneDorata = new AdvObject(67, "Incisione", "Un'antica incisione dorata con strani geroglifici.\n"
                + "Conosco questa lingua posso tradurla.");
        incisioneDorata.setText("Recita: 'Guarda verso l'alto o cercatore di verità\ne lascia che la luce ti guidi "
                + "verso la chiave.\nIl sole rivela il passato, la luna illumina il presente,\nla stella indica il futuro.\n"
                + "Trova l'ordine corretto in cui premere i bottoni\nalle spalle delle statue e la porta del sapere ti sarà rivelata'");
        incisioneDorata.setAlias(new String[]{"incisione"});
        SalaStatue.getObjects().add(incisioneDorata);
        incisioneDorata.setLookable(true);
        incisioneDorata.setReadable(true);

        AdvObject pulsanteSole = new AdvObject(13, "PulsanteSole", "Un pulsante con incisio un Sole dorato.");
        pulsanteSole.setAlias(new String[]{"bottonesole", "pulsantesole"});
        SalaStatue.getObjects().add(pulsanteSole);
        pulsanteSole.setPushable(true);
        pulsanteSole.setPushed(false);
        pulsanteSole.setLookable(true);

        AdvObject pulsanteLuna = new AdvObject(14, "PulsanteLuna", "Un pulsante con incisa una Luna argentea.");
        pulsanteLuna.setAlias(new String[]{"bottoneluna", "pulsanteluna"});
        SalaStatue.getObjects().add(pulsanteLuna);
        pulsanteLuna.setPushable(true);
        pulsanteLuna.setPushed(false);
        pulsanteLuna.setLookable(true);

        AdvObject pulsanteStella = new AdvObject(15, "PulsanteStella", "Un pulsante con incisa una stella.");
        pulsanteStella.setAlias(new String[]{"bottonestella", "pulsantestella"});
        SalaStatue.getObjects().add(pulsanteStella);
        pulsanteStella.setPushable(true);
        pulsanteStella.setPushed(false);
        pulsanteStella.setLookable(true);

        AdvObject portaCripta = new AdvObject(16, "PortaCripta", "Un'enorme porta non mi permette di andare avanti. Devo cercare di aprirla.");
        portaCripta.setAlias(new String[]{"porta", "portone"});
        SalaStatue.getObjects().add(portaCripta);
        portaCripta.setOpen(false);
        portaCripta.setOpenable(true);

        //SalaIncisione
        AdvObject torcia = new AdvObject(17, "Torcia", "Una piccola torcia.\n"
                + "Deve averla lasciata qualche altro esploratore.\n"
                + "Presenta anche la funzione UV, forse mi sarà utile...");
        torcia.setAlias(new String[]{"luce", "torcia"});
        SalaIncisione.getObjects().add(torcia);
        CameraScrigno.getObjects().add(torcia);
        torcia.setUsable(true);
        torcia.setPickupable(true);
        torcia.setLookable(true);
        getObjects().add(torcia);

        AdvObject incisioneFuturo = new AdvObject(18, "IncisioneFuturo", "Una strana incisione che raffigura un antico rito egizio\nusato per la predizione del futuro.\n"
                + "Non riesco a capire.\n\n"
                + "Forse c'è qualcosa che non vedo...\n\n"
                + "(digita n per uscire dalla visualizzazione)");
        incisioneFuturo.setAlias(new String[]{"incisione"});
        SalaIncisione.getObjects().add(incisioneFuturo);
        incisioneFuturo.setLookable(true);

        //SalaCripta
        AdvObject tavolaIncisa = new AdvObject(19, "TavolaIncisa", "Un'antica tavola genealogia scritta con caratteri molto antichi.");
        tavolaIncisa.setText("Recita:\n'Nel regno dei morti, l'antica saggezza risiede nelle vite e nei destini\ndei nostri progenitori.\n"
                + "Segui il filo del tempo, rispetta le generazioni,\ne il cammino verso il tesoro si rivelerà...\n\n"
                + "Ma bada bene, non tutto quello che c'è qui ti servirà per arrivarci...'");
        tavolaIncisa.setAlias(new String[]{"tavola"});
        SalaCripta.getObjects().add(tavolaIncisa);
        tavolaIncisa.setLookable(true);
        tavolaIncisa.setReadable(true);

        AdvObject sarcofagoCorona = new AdvObject(20, "SarcofagoCorona", "Un sarcofago con incisa una corona.\n\n"
                + "Sembra muoversi...");
        sarcofagoCorona.setAlias(new String[]{"sarcofagocorona"});
        SalaCripta.getObjects().add(sarcofagoCorona);
        sarcofagoCorona.setPushable(true);
        sarcofagoCorona.setOpen(false);
        sarcofagoCorona.setOpenable(false);
        sarcofagoCorona.setPushed(false);
        sarcofagoCorona.setLookable(true);

        AdvObject sarcofagoLibro = new AdvObject(21, "SarcofagoLibro", "Un sarcofago con inciso un libro.");
        sarcofagoLibro.setAlias(new String[]{"sarcofagolibro"});
        SalaCripta.getObjects().add(sarcofagoLibro);
        sarcofagoLibro.setPushable(true);
        sarcofagoLibro.setOpen(false);
        sarcofagoLibro.setOpenable(false);
        sarcofagoLibro.setPushed(false);
        sarcofagoLibro.setLookable(true);

        AdvObject sarcofagoArpa = new AdvObject(22, "SarcofagoArpa", "Un sarcofago con incisa un'arpa.\n\n"
                + "Si sentono degli spifferi dall'altra parte...");
        sarcofagoArpa.setAlias(new String[]{"sarcofagoarpa"});
        SalaCripta.getObjects().add(sarcofagoArpa);
        sarcofagoArpa.setPushable(true);
        sarcofagoArpa.setOpen(false);
        sarcofagoArpa.setOpenable(false);
        sarcofagoArpa.setPushed(false);
        sarcofagoArpa.setLookable(true);

        AdvObject sarcofagoCroce = new AdvObject(23, "SarcofagoCroce", "Un sarcofago con incisa una croce.");
        sarcofagoCroce.setAlias(new String[]{"sarcofagocroce"});
        SalaCripta.getObjects().add(sarcofagoCroce);
        sarcofagoCroce.setPushable(true);
        sarcofagoCroce.setOpen(false);
        sarcofagoCroce.setOpenable(false);
        sarcofagoCroce.setPushed(false);
        sarcofagoCroce.setLookable(true);

        AdvObject sarcofagoCerchio = new AdvObject(24, "SarcofagoCerchio", "Un sarcofago con incisa una luna.");
        sarcofagoCerchio.setAlias(new String[]{"sarcofagocerchio"});
        SalaCripta.getObjects().add(sarcofagoCerchio);
        sarcofagoCerchio.setPushable(true);
        sarcofagoCerchio.setOpen(false);
        sarcofagoCerchio.setOpenable(false);
        sarcofagoCerchio.setPushed(false);
        sarcofagoCerchio.setLookable(true);

        AdvObjectContainer sarcofagoStella = new AdvObjectContainer(25, "SarcofagoStella", "Un sarcofago con incisa una stella.\n\n"
                + "Si sentono degli strani rumori provenire dal suo interno...");
        sarcofagoStella.setAlias(new String[]{"sarcofagostella"});
        SalaCripta.getObjects().add(sarcofagoStella);
        sarcofagoStella.setPushable(true);
        sarcofagoStella.setOpen(true);
        sarcofagoStella.setOpenable(true);
        sarcofagoStella.setPushed(false);
        sarcofagoStella.setLookable(true);

        AdvObject lente = new AdvObject(68, "Lente", "Sembra la punta di un cannocchiale.\n\n"
                + "Non ho idea di cosa potrei farmene...");
        lente.setAlias(new String[]{"lente"});
        sarcofagoStella.addObject(lente);
        SalaOsservatorio.getObjects().add(lente);
        lente.setPickupable(true);
        lente.setLookable(false);
        lente.setUsable(true);
        lente.setHidden(true);
        lente.setTaken(false);
        getObjects().add(lente);

        //CameraSapienza
        AdvObject libroAntico = new AdvObject(26, "LibroAntico", "Un antico libro aperto su un piedistallo.");
        libroAntico.setText("La pagina in cui è aperto dice:\n\n'Il passato ci illumina, il presente costruisce le nostre radici,\nil futuro scorre davanti a noi.\n"
                + "\nTrova il filo che lega l'antico al nuovo,\ne la sapienza della piramide ti sarà rivelata...'");
        libroAntico.setAlias(new String[]{"libro"});
        CameraSapienza.getObjects().add(libroAntico);
        libroAntico.setLookable(true);
        libroAntico.setReadable(true);

        AdvObject chiaveOro = new AdvObject(27, "ChiaveOro", "Un'antica chiave completamente in oro.\n"
                + "Forse aprirà qualcosa di utile...o potrei solo rivenderla su ebay");
        chiaveOro.setAlias(new String[]{"chiave"});
        CameraSapienza.getObjects().add(chiaveOro);
        CameraScrigno.getObjects().add(chiaveOro);
        chiaveOro.setPickupable(true);
        chiaveOro.setUsable(true);
        chiaveOro.setLookable(true);
        getObjects().add(chiaveOro);

        AdvObject dipintoAlbero = new AdvObject(28, "DipintoALbero", "Un dipinto che rappresenta un albero rigoglioso con radici profonde.\n"
                + "Credo sia un riferimento al passato, come radice di conoscenza.");
        dipintoAlbero.setAlias(new String[]{"dipintoalbero", "quadroalbero"});
        CameraSapienza.getObjects().add(dipintoAlbero);
        dipintoAlbero.setLookable(true);

        AdvObject dipintoFiume = new AdvObject(29, "DipintoFiume", "Un dipinto che rappresenta un fiume che scorre tra le montagne.\n"
                + "Forse indica il presente e la guida costante del flusso della vita.");
        dipintoFiume.setAlias(new String[]{"dipintofiume", "quadrofiume"});
        CameraSapienza.getObjects().add(dipintoFiume);
        dipintoFiume.setLookable(true);

        AdvObject dipintoFiamma = new AdvObject(30, "DipintoFiamma", "Un dipinto che rappresenta una fiamma ardente.\n"
                + "Un libro diceva che gli egizi usavano la fiamma per far riferimento al futuro.");
        dipintoFiamma.setAlias(new String[]{"dipintofiamma", "quadrofiamma"});
        CameraSapienza.getObjects().add(dipintoFiamma);
        dipintoFiamma.setLookable(true);

        AdvObject dipintoColomba = new AdvObject(31, "DipintoColomba", "Un dipinto che rappresenta una colomba bianca in volo.\n"
                + "Non guardare me...non ho idea di che significhi.");
        dipintoColomba.setAlias(new String[]{"dipintocolomba", "quadrocolomba"});
        CameraSapienza.getObjects().add(dipintoColomba);
        dipintoColomba.setLookable(true);

        AdvObject dipintoLibro = new AdvObject(32, "DipintoLibro", "Un dipinto che rappresenta un libro aperto con simboli astratti.\n"
                + "Forse si riferisce alla conoscenza o a rituali magici.");
        dipintoLibro.setAlias(new String[]{"dipintolibro", "quadrolibro"});
        CameraSapienza.getObjects().add(dipintoLibro);
        dipintoLibro.setLookable(true);

        AdvObject dipintoStella = new AdvObject(33, "DipintoStella", "Un dipinto che rappresenta una stella luminosa nel cielo notturno.\n"
                + "Non ho la più pallida idea di cosa significhi.");
        dipintoStella.setAlias(new String[]{"dipintostella", "quadrostella"});
        CameraSapienza.getObjects().add(dipintoStella);
        dipintoStella.setLookable(true);

        AdvObject pietraAlbero = new AdvObject(34, "PietraAlbero", "Una pietra con il simbolo dell'albero.");
        pietraAlbero.setAlias(new String[]{"pietraalbero"});
        CameraSapienza.getObjects().add(pietraAlbero);
        pietraAlbero.setPushable(true);
        pietraAlbero.setPushed(false);
        pietraAlbero.setLookable(true);

        AdvObject pietraFiume = new AdvObject(35, "PietraFiume", "Una pietra con il simbolo di un fiume.");
        pietraFiume.setAlias(new String[]{"pietrafiume"});
        CameraSapienza.getObjects().add(pietraFiume);
        pietraFiume.setPushable(true);
        pietraFiume.setPushed(false);
        pietraFiume.setLookable(true);

        AdvObject pietraFiamma = new AdvObject(36, "PietraFiamma", "Una pietra con il simbolo di una fiamma.");
        pietraFiamma.setAlias(new String[]{"pietrafiamma"});
        CameraSapienza.getObjects().add(pietraFiamma);
        pietraFiamma.setPushable(true);
        pietraFiamma.setPushed(false);
        pietraFiamma.setLookable(true);

        AdvObject pietraColomba = new AdvObject(37, "PietraColomba", "Una pietra con il simbolo di una colomba.");
        pietraColomba.setAlias(new String[]{"pietracolomba"});
        CameraSapienza.getObjects().add(pietraColomba);
        pietraColomba.setPushable(true);
        pietraColomba.setPushed(false);
        pietraColomba.setLookable(true);

        AdvObject pietraLibro = new AdvObject(38, "Pietralibro", "Una pietra con il simbolo di un libro.");
        pietraLibro.setAlias(new String[]{"pietralibro"});
        CameraSapienza.getObjects().add(pietraLibro);
        pietraLibro.setPushable(true);
        pietraLibro.setPushed(false);
        pietraLibro.setLookable(true);

        AdvObject pietraStella = new AdvObject(39, "PietraStella", "Una pietra con lo stemma della Juve.\n"
                + "No scerzo...con il simbolo di una stella.");
        pietraStella.setAlias(new String[]{"pietrastella"});
        CameraSapienza.getObjects().add(pietraStella);
        pietraStella.setPushable(true);
        pietraStella.setPushed(false);
        pietraStella.setLookable(true);

        //CameraScrigno
        AdvObjectContainer scrigno = new AdvObjectContainer(40, "Scrigno", "Un grande scrigno dorato, richiede una chiave per essere aperto.");
        scrigno.setAlias(new String[]{"forziere", "baule", "tesoro", "scrigno"});
        CameraScrigno.getObjects().add(scrigno);
        scrigno.setOpenable(true);
        scrigno.setLookable(true);

        AdvObject incisionePresente = new AdvObject(41, "IncisionePresente", "Un'altra incisione, questa sembra rappresentare il presente.\n"
                + "Magari nasconde qualcosa...\n\n"
                + "(digita s per uscire dalla visualizzazione)");
        incisionePresente.setAlias(new String[]{"incisione"});
        scrigno.addObject(incisionePresente);
        incisionePresente.setLookable(true);

        //SECONDO PIANO
        //SalaRitualeAstrale
        AdvObject tavolettaCerimoniale = new AdvObject(42, "TavolettaCerimoniale", "Un'antica tavoletta cerimoniale abbastanza malandata.\n"
                + "Mi sembra di leggere dei caratteri sumeri.");
        tavolettaCerimoniale.setText("Recita: 'Nel cerchio degli astri, l'antica"
                + " saggezza\nsi manifesta nei movimenti celesti che guidano il destino.\nSintonizza la tua mente con le stelle,\ndecifra l'armonia celeste,\ne il "
                + "portale verso il sapere si aprirà...'\n\n"
                + "Credo che questi cerchi nascondano qualcosa\nche mi sarà utile per aprire quella porta.");
        tavolettaCerimoniale.setAlias(new String[]{"tavoletta", "tavolettacerimoniale"});
        SalaRitualeAstrale.getObjects().add(tavolettaCerimoniale);
        tavolettaCerimoniale.setLookable(true);
        tavolettaCerimoniale.setReadable(true);

        AdvObject piedistallo = new AdvObject(43, "Piedistallo", "Che dire...un semplice tavolino.");
        piedistallo.setAlias(new String[]{"tavolino", "tavolo", "piedistallo"});
        SalaRitualeAstrale.getObjects().add(piedistallo);
        piedistallo.setLookable(true);

        AdvObject cerchioSaturno = new AdvObject(44, "CerchioSaturno", "Cerchio che rappresenta Saturno.\n"
                + "Associato alla comunicazion e alla mente.");
        cerchioSaturno.setAlias(new String[]{"cerchiosaturno"});
        SalaRitualeAstrale.getObjects().add(cerchioSaturno);
        cerchioSaturno.setLookable(true);

        AdvObject cerchioMarte = new AdvObject(45, "CerchioMarte", "Cerchio che rappresenta mArte.\n"
                + "Associato all'azione e alla determinazione.");
        cerchioMarte.setAlias(new String[]{"cerchiomarte"});
        SalaRitualeAstrale.getObjects().add(cerchioMarte);
        cerchioMarte.setLookable(true);

        AdvObject cerchioPlutone = new AdvObject(46, "CerchioPlutone", "Cerchio che rappresenta Plutone.\n"
                + "Associato alla creatività e alla libertà.");
        cerchioPlutone.setAlias(new String[]{"cerchioplutone"});
        SalaRitualeAstrale.getObjects().add(cerchioPlutone);
        cerchioPlutone.setLookable(true);

        AdvObject cerchioVenere = new AdvObject(47, "CerchioVenere", "Cerchio che rappresenta venEre.\n"
                + "Associato all'amore e all'equilibrio.");
        cerchioVenere.setAlias(new String[]{"cerchiovenere"});
        SalaRitualeAstrale.getObjects().add(cerchioVenere);
        cerchioVenere.setLookable(true);

        AdvObject cerchioTerra = new AdvObject(48, "CerchioTerra", "Cerchio che rappresenta la teRra\n"
                + "Associata alla vita e all'armonia.");
        cerchioTerra.setAlias(new String[]{"cerchioterra"});
        SalaRitualeAstrale.getObjects().add(cerchioTerra);
        cerchioTerra.setLookable(true);

        AdvObject cerchioGiove = new AdvObject(49, "CerchioGiove", "Cerchio che rappresenta giovE.\n"
                + "Associato alla saggezza e alla prosperità.");
        cerchioGiove.setAlias(new String[]{"cerchiogiove"});
        SalaRitualeAstrale.getObjects().add(cerchioGiove);
        cerchioGiove.setLookable(true);

        AdvObject portaSalaTesoro = new AdvObject(66, "PortaSalaTesoro", "Un'enorme porta rivestita d'oro!\n"
                + "Deve celare qualcosa di veramente prezioso.");
        portaSalaTesoro.setAlias(new String[]{"porta"});
        SalaRitualeAstrale.getObjects().add(portaSalaTesoro);
        portaSalaTesoro.setOpenable(true);
        portaSalaTesoro.setOpen(false);
        portaSalaTesoro.setLookable(true);
        portaSalaTesoro.setUsable(true);

        //SalaTesoro
        AdvObjectContainer sarcofago = new AdvObjectContainer(50, "Sarcofago", "L'antica tomba di colui a cui è stata dedicata la piramide.");
        sarcofago.setAlias(new String[]{"tomba", "sarcofago"});
        SalaTesoro.getObjects().add(sarcofago);
        sarcofago.setOpen(false);
        sarcofago.setOpenable(true);
        sarcofago.setLookable(true);

        AdvObject chiavePortaTesoro = new AdvObject(55, "ChiavePortaTesoro", "Una chiave ricoperta da pietre preziose, e muffa...");
        chiavePortaTesoro.setAlias(new String[]{"chiave", "chiavetesoro", "chiaveporta"});
        sarcofago.addObject(chiavePortaTesoro);
        chiavePortaTesoro.setLookable(true);
        chiavePortaTesoro.setPickupable(true);
        chiavePortaTesoro.setUsable(true);
        chiavePortaTesoro.setHidden(true);

        AdvObject pergamena = new AdvObject(54, "Pergamena", "Una pergamena scritta in Egiziano antico.");
        pergamena.setText("Recita: 'Ripercorri il cammino dell'uomo e scopri cosa lo ha guidato\n"
                + "sin dalle sue origini nella corretta via...\n\n"
                + "Trova la rappresentazione corretta e premi il suo pulsante\n"
                + "o il tuo viaggio si fermerà qui...'");
        pergamena.setAlias(new String[]{"papiro", "scritta", "pergamena"});
        SalaTesoro.getObjects().add(pergamena);
        pergamena.setLookable(true);
        pergamena.setReadable(true);

        AdvObject primoPilastro = new AdvObject(51, "PrimoPilastro", "Il primo pilastro mostra una scena di antichi guerrieri\nche "
                + "attaccano una piccola cittadina,\ntrafugando le sue ricchezze.\n\n"
                + "Rappresenta la forza e il caos, senza saggezza.");
        primoPilastro.setAlias(new String[]{"primacolonna", "primopilastro"});
        SalaTesoro.getObjects().add(primoPilastro);
        primoPilastro.setLookable(true);
        primoPilastro.setPushable(true);

        AdvObject secondoPilastro = new AdvObject(52, "SecondoPilastro", "Il secondo pilastro raffigura un uomo solitario\nche medita sotto un albero "
                + "secolare.\n\n"
                + "Simboleggia la saggezza e la contemplazione.");
        secondoPilastro.setAlias(new String[]{"secondacolonna", "secondopilastro"});
        SalaTesoro.getObjects().add(secondoPilastro);
        secondoPilastro.setLookable(true);
        secondoPilastro.setPushable(true);

        AdvObject terzoPilastro = new AdvObject(53, "Terzo Pilastro", "Il terzo pilastro raffigura una scena di negoziatori e commercianti\n"
                + "che scambiano merci,\nsiboleggiando il commercio e l'accordo.\n\n"
                + "Ma non necessariamente la giustizia.");
        terzoPilastro.setAlias(new String[]{"terzacolonna", "terzopilastro"});
        SalaTesoro.getObjects().add(terzoPilastro);
        terzoPilastro.setLookable(true);
        terzoPilastro.setPushable(true);

        //SalaOsservatorio
        AdvObject scrittoio = new AdvObject(63, "Scrittoio", "Un semplice scrittoio.\nSe mi sedessi sono sicuro che crollerebbe.");
        scrittoio.setAlias(new String[]{"scrivania", "tavolino", "scrittoio"});
        SalaOsservatorio.getObjects().add(scrittoio);
        scrittoio.setLookable(true);

        AdvObject manoscritto = new AdvObject(64, "ManoScritto", "Un manoscritto davvero consumanto.");
        manoscritto.setText("Recita: \n"
                + "'Fai uso dell'antico cannocchiale in questa stanza\n"
                + "per ottenere il tesoro della piramide.\n\n"
                + "Per farlo usa la lente che si trova nel sarcofago\n"
                + "con la forma di stella nella Cripta degli antenati...'");
        manoscritto.setAlias(new String[]{"manoscritto", "papiro", "pergamena"});
        SalaOsservatorio.getObjects().add(manoscritto);
        manoscritto.setLookable(true);
        manoscritto.setReadable(true);

        AdvObject telescopio = new AdvObject(65, "Telescopio", "Un antico telescopio, già orientato...");
        telescopio.setAlias(new String[]{"telescopio", "cannocchiale"});
        SalaOsservatorio.getObjects().add(telescopio);
        telescopio.setUsable(true);
        telescopio.setLookable(true);
        getObjects().add(telescopio);

        if (GameDescription.loadedGame) {
            for (AdvObject object : getObjects()) {
                int objectID = object.getId();
                if (Database.loadTakenObjects(objectID)) {
                    getInventory().add(object);
                } else if (Database.loadUsedObjects(objectID)) {
                    switch (objectID) {
                        case 3 -> {
                            getInventory().remove(chiaveIngresso);
                            portaIngresso.setOpen(true);
                        }
                        case 9 -> {
                            getInventory().remove(simbolo1);
                        }
                        case 7 -> {
                            getInventory().remove(simbolo2);
                            portaAtrioDestra.setOpen(true);
                        }
                        case 27 -> {
                            getInventory().remove(chiaveOro);
                            scrigno.setOpen(true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void Introduction() {
        String introduction = readFile("./src/main/resources/Init/Introduction.rtf");
        EngineGUI.introTextSet(introduction);
    }

    @Override
    public void Conclusion() {
        String conclusion = readFile("./src/main/resources/Init/Conclusion.rtf");
        EngineGUI.conclusionTextSet(conclusion);
    }

    @Override
    public void nextMove(ParserOutput p, EngineGUI frame) {
        Scanner scanner = new Scanner(System.in);        
        
        if (p.getCommand() == null) {
            frame.NarrationTextAreaSetText("Mi dispiace ma non lo posso fare!");
        } else {

            switch (p.getCommand().getType()) {
                case NORTH -> {
                    if (getCurrentRoom().getNorth() != null) {
                        if (getCurrentRoom().getNorth().isLocked() == false) {
                            if (getCurrentRoom().getId() == 1) {
                                setCurrentRoom(getCurrentRoom().getNorth());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                                if (Database.loadTakenObjects(7)) {
                                    frame.ViewAreaSetViewPath("/Img/view/SpostaStatuaPrendiSimbolo.png");
                                }
                            } else if (getCurrentRoom().getId() == 8) {
                                setCurrentRoom(getCurrentRoom().getNorth());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                            } else {
                                setCurrentRoom(getCurrentRoom().getNorth());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                            }
                        } else if (getCurrentRoom().getId() == 11) {
                            String s;
                            s = (String) JOptionPane.showInputDialog(frame, "Inserisci la parola segreta:\n", "PAROLA SEGRETA", JOptionPane.PLAIN_MESSAGE, null, null, "");
                            if (s != null) {
                                if (s.equalsIgnoreCase("sapere")) {
                                    getCurrentRoom().getNorth().setLocked(false);
                                    Database.saveObject(66, "portaSalaTesoro", true);
                                    frame.NarrationTextAreaSetText("Hai sbloccato la porta!");
                                    Sound.playEffect("./src/main/resources/Sounds/LockSoundEffect.wav");
                                } else {
                                    frame.NarrationTextAreaSetText("Parola NON CORRETTA.");
                                }
                            } else {
                                frame.NarrationTextAreaSetText("Non riesco a trovare questo oggetto");
                            }

                        } else {
                            frame.NarrationTextAreaSetText("Il tuo cammino è bloccato!");
                        }
                    } else {
                        frame.NarrationTextAreaSetText("Non puoi andare in questa direzione.");
                    }
                }
                case SOUTH -> {
                    if (getCurrentRoom().getSouth() != null) {
                        if (getCurrentRoom().getSouth().isLocked() == false) {
                            if (getCurrentRoom().getId() == 2) {
                                setCurrentRoom(getCurrentRoom().getSouth());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                                if (Database.loadTakenObjects(1)) {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiTorciaPrendiChiave.png");
                                } else {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiChiave.png");
                                }
                            } else if (getCurrentRoom().getId() == 3) {
                                setCurrentRoom(getCurrentRoom().getSouth());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                                if (Database.loadTakenObjects(17)) {
                                    frame.ViewAreaSetViewPath("/Img/view/SalaIncisionePrendiTorcia.png");
                                }
                            } else {
                                setCurrentRoom(getCurrentRoom().getSouth());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                            }
                        } else if (getCurrentRoom().getId() == 1) {
                            frame.NarrationTextAreaSetText("Davvero...\n"
                                    + "Sei appena arrivato e già vuoi andartene?");
                        } else {
                            frame.NarrationTextAreaSetText("Il tuo cammino è bloccato!");
                        }
                    } else {
                        frame.NarrationTextAreaSetText("Non puoi andare in questa direzione.");
                    }
                }
                case EAST -> {
                    if (getCurrentRoom().getEast() != null) {
                        if (getCurrentRoom().getEast().isLocked() == false) {
                            if (getCurrentRoom().getId() == 6) {
                                setCurrentRoom(getCurrentRoom().getEast());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                                if (Database.loadTakenObjects(7)) {
                                    frame.ViewAreaSetViewPath("/Img/view/SpostaStatuaPrendiSimbolo.png");
                                }
                            } else {
                                setCurrentRoom(getCurrentRoom().getEast());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                            }
                        } else {
                            frame.NarrationTextAreaSetText("Il tuo cammino è bloccato!");
                        }
                    } else {
                        frame.NarrationTextAreaSetText("Non puoi andare in questa direzione.");
                    }
                }
                case WEST -> {
                    if (getCurrentRoom().getWest() != null) {
                        if (getCurrentRoom().getWest().isLocked() == false) {
                            if (getCurrentRoom().getId() == 2) {
                                setCurrentRoom(getCurrentRoom().getWest());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                                if (Database.loadTakenObjects(8) && Database.loadTakenObjects(9)) {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiSimboloPrendiBiglietto.png");
                                } else if (Database.loadTakenObjects(8)) {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiBiglietto.png");
                                } else if (Database.loadTakenObjects(9)) {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiSimbolo.png");
                                }
                            } else if (getCurrentRoom().getId() == 3) {
                                setCurrentRoom(getCurrentRoom().getWest());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                                if (Database.loadTakenObjects(7)) {
                                    frame.ViewAreaSetViewPath("/Img/view/SpostaStatuaPrendiSimbolo.png");
                                }
                            } else {
                                setCurrentRoom(getCurrentRoom().getWest());
                                frame.ViewAreaSetView();
                                frame.MiniMapAreaSetView();
                                frame.CurrentRoomUpdate();
                                frame.RoomDescriptionUpdate();
                            }
                        } else {
                            frame.NarrationTextAreaSetText("Il tuo cammino è bloccato!");
                        }
                    } else {
                        frame.NarrationTextAreaSetText("Non puoi andare in questa direzione.");
                    }
                }
                case USE -> {
                    AdvObject object = p.getObject();

                    if (p.getObject() != null) {
                        if (p.getObject().isUsable()) {
                            switch (object.getId()) {
                                case 1 -> {
                                    if (getInventory().contains(object)) {
                                        frame.NarrationTextAreaSetText("La torcia è spenta, usi il tuo zippo per accenderla.\n"
                                                + "Ora è accesa.\n\n");

                                    } else {
                                        frame.NarrationTextAreaSetText("Dovresti prima raccoglierla la torcia, non trovi?");
                                    }
                                }
                                case 3 -> {
                                    if (getCurrentRoom().getId() == 1 && getInventory().contains(object) && !Database.loadUsedObjects(3)) {
                                        frame.NarrationTextAreaSetText("Hai usato " + object.getName() + ".\n"
                                                + "La porta davanti a te è aperta!");
                                        getInventory().remove(object);
                                        getCurrentRoom().getNorth().setLocked(false);
                                        Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                        p.getObject().setUsable(false);

                                    } else {
                                        frame.NarrationTextAreaSetText("Hai già utilizzato la chiave per aprire la porta.");
                                        getCurrentRoom().getNorth().setLocked(false);
                                    }
                                }
                                case 9 -> {
                                    if (getCurrentRoom().getId() == 2 && !Database.loadUsedObjects(9) && Database.loadTakenObjects(9)) {
                                        frame.NarrationTextAreaSetText("Hai usato " + object.getName() + ".\n"
                                                + "La porta sembra comunque chiusa, serve un altro simbolo...");
                                        Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                    } else if (!Database.loadTakenObjects(9) && getCurrentRoom().getId() == 6) {
                                        frame.NarrationTextAreaSetText("Dovresti prima raccogliere l'Occhio, non credi?");
                                    } else if (getCurrentRoom().getId() == 2 && Database.loadTakenObjects(9)) {
                                        frame.NarrationTextAreaSetText("Hai già utilizzato l'Occhio per aprire la porta.");
                                    } else if (getCurrentRoom().getId() == 6) {
                                        frame.NarrationTextAreaSetText("Non credo sia il posto giusto per usare l'Occhio di Horus.");
                                    } else {
                                        frame.NarrationTextAreaSetText("Non riesco a trovare questo oggetto.");
                                    }
                                }
                                case 7 -> {
                                    if (getCurrentRoom().getId() == 2 && !Database.loadUsedObjects(7)) {
                                        if (Database.loadUsedObjects(9)) {
                                            frame.NarrationTextAreaSetText("Hai usato " + object.getName() + ".\n"
                                                    + "Ora la porta è aperta.");
                                            Sound.playEffect("./src/main/resources/Sounds/LockSoundEffect.wav");
                                            Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                            getCurrentRoom().getEast().setLocked(false);
                                            p.getObject().setUsable(false);
                                        } else {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Hai già utilizzato Ankh per aprire la porta.");
                                    }
                                }
                                case 17 -> {
                                    if (!getInventory().contains(object)) {
                                        frame.NarrationTextAreaSetText("Dovresti prima raccoglierla la torcia, non credi?");
                                    } else if (getCurrentRoom().getId() == 5 && incisioneFuturoExaminated == true) {
                                        frame.NarrationTextAreaSetText("Hai usato " + object.getName() + ".\n"
                                                + "E' apparso il numero 3.\n\n"
                                                + "(digita 'n' per uscire dalla visualizzazione)");
                                        frame.ViewAreaSetViewPath("/Img/view/EsaminaIncisioneUsaTorcia.png");
                                    } else if (getCurrentRoom().getId() == 9 && incisionePassatoExaminated == true) {
                                        frame.NarrationTextAreaSetText("Hai usato " + object.getName() + ".\n"
                                                + "E' apparso il numero 1.\n\n"
                                                + "(digita 's' per uscire dalla visualizzazione)");
                                        frame.ViewAreaSetViewPath("/Img/view/IncisionePassatoUsaTorcia.png");
                                    } else {
                                        frame.NarrationTextAreaSetText("Finalmente un po' di luce!");
                                    }
                                }
                                case 27 -> {
                                    if (!Database.loadUsedObjects(27)) {
                                        if (getCurrentRoom().getId() == 9 && getInventory().contains(object)) {
                                            frame.NarrationTextAreaSetText("Hai usato " + p.getObject().getName() + ".\n"
                                                    + "Sembra che lo scrigno ora sia sbloccato...");
                                            Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                        } else if (!getInventory().contains(object)) {
                                            frame.NarrationTextAreaSetText("Credo tu debba prima raccogliere la chiave no?");
                                        } else {
                                            frame.NarrationTextAreaSetText("Questo oggetto non può essere usato qui.");
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Hai già usato " + p.getObject().getName() + ", lo scrigno è aperto.");
                                    }
                                }
                                case 55 -> {
                                    if (!Database.loadUsedObjects(55)) {
                                        if (object.isUsable() == true && getCurrentRoom().getId() == 12) {
                                            frame.NarrationTextAreaSetText("Hai usato: " + object.getName() + ".\n"
                                                    + "Ora le scale sono sbloccate!");
                                            getCurrentRoom().getNorth().setLocked(false);
                                        }
                                    }
                                }
                                case 65 -> {
                                    if (Database.loadUsedObjects(68)) {
                                        if (getCurrentRoom().getId() == 14 && object.isUsable() == true) {
                                            frame.ViewAreaSetViewPath("/Img/view/VisualeTelescopio.png");
                                            Sound.playEffect("./src/main/resources/Sounds/StarSound.wav");
                                            EngineGUI.hideLabels();
                                            this.Conclusion();
                                        }
                                    }
                                }
                                case 68 -> {
                                    if (getCurrentRoom().getId() == 14 && object.isUsable() == true) {
                                        frame.NarrationTextAreaSetText("Hai usato: " + object.getName() + ".\n"
                                                + "Ora potrai usare il telescopio!");
                                        Database.saveObject(object.getId(), object.getName(), true);
                                    }
                                }
                            }
                        } else {
                            if (Database.loadUsedObjects(p.getObject().getId())) {
                                frame.NarrationTextAreaSetText("Hai già utilizzato " + p.getObject().getName());
                            } else {
                                frame.NarrationTextAreaSetText("Questo oggetto non può essere usato.");
                            }
                        }
                    } else {
                        frame.NarrationTextAreaSetText("Non riesco a trovare quest'oggetto.");
                    }
                }
                case PRESS -> {
                    if (p.getObject() != null) {
                        if (p.getObject().isPushable()) {
                            switch (p.getObject().getId()) {
                                case 2 -> {
                                    if (mattonePushed == false) {
                                        if (Database.loadTakenObjects(1)) {
                                            frame.ViewAreaSetViewPath("/Img/view/PrendiTorciaSpostaMattone.png");
                                        } else {
                                            frame.ViewAreaSetViewPath("/Img/view/SpostaMattone.png");
                                        }
                                        Sound.playEffect("./src/main/resources/Sounds/BrickSliding.wav");
                                        frame.NarrationTextAreaSetText("Hai spostato " + p.getObject().getName() + ".\n"
                                                + "Sembra nascondesse un'antica chiave, dovresti prenderla...");
                                        mattonePushed = true;
                                    } else {
                                        frame.NarrationTextAreaSetText("Hai già spostato il mattone, dove vuoi che vada?");
                                    }
                                }
                                case 5 -> {
                                    if (statuAtrioPushed == false) {
                                        Sound.playEffect("./src/main/resources/Sounds/StatueSliding.wav");
                                        frame.ViewAreaSetViewPath("/Img/view/SpostaStatua.png");
                                        frame.NarrationTextAreaSetText("Hai spostato " + p.getObject().getName() + ".\n"
                                                + "C'è un altro di quegli strani simboli dietro...");
                                        statuAtrioPushed = true;
                                    } else {
                                        frame.NarrationTextAreaSetText("Hai già spostato la statua, dove vuoi che vada?");
                                    }
                                }
                                case 13 -> {
                                    if (!Database.loadUsedObjects(15)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        pulsante2Pushed = true;
                                        if (pulsante1Pushed == true && order == 1) {
                                            order = 2;
                                        }
                                        if (pulsante1Pushed == true && pulsante3Pushed == true) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            pulsante1Pushed = false;
                                            pulsante2Pushed = false;
                                            pulsante3Pushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Hai già premuto " + p.getObject().getName() + ", la porta è aperta.");
                                    }
                                }
                                case 14 -> {
                                    if (!Database.loadUsedObjects(15)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        pulsante1Pushed = true;
                                        order = 1;
                                        if (pulsante2Pushed == true && pulsante3Pushed == true) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            pulsante1Pushed = false;
                                            pulsante2Pushed = false;
                                            pulsante3Pushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Hai già premuto " + p.getObject().getName() + ", la porta è aperta.");
                                    }
                                }

                                case 15 -> {
                                    if (!Database.loadUsedObjects(15)) {
                                        if (pulsante1Pushed == true && pulsante2Pushed == true && order == 2) {
                                            frame.NarrationTextAreaSetText("L'ordine era corretto!\n"
                                                    + "Ho sentito uno strano marchingegno ad est, forse la porta è aperta...");
                                            Sound.playEffect("./src/main/resources/Sounds/GearsSound.wav");
                                            getCurrentRoom().getEast().setLocked(false);
                                            Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                        } else if ((pulsante1Pushed == false && pulsante2Pushed == false) || (pulsante1Pushed == true && pulsante2Pushed == false) || (pulsante1Pushed == false && pulsante2Pushed == true)) {
                                            frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                            pulsante3Pushed = true;
                                        } else {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            pulsante1Pushed = false;
                                            pulsante2Pushed = false;
                                            pulsante3Pushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Hai già premuto " + p.getObject().getName() + ", la porta è aperta.");
                                    }
                                }
                                case 34 -> {
                                    if (!Database.loadUsedObjects(35)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        pietraAlberoPushed = true;
                                        if (pietraFiammaPushed == true && sequence == 1) {
                                            sequence = 2;
                                        }
                                        if ((pietraFiammaPushed == true && pietraFiumePushed == true)) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            pulsante1Pushed = false;
                                            pulsante2Pushed = false;
                                            pulsante3Pushed = false;
                                            isPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + ", la porta è aperta.");
                                    }
                                }
                                case 35 -> {
                                    if (!Database.loadUsedObjects(35)) {
                                        if (pietraFiammaPushed == true && pietraAlberoPushed == true && sequence == 2) {
                                            frame.NarrationTextAreaSetText("Qualcosa si è sbloccato!\n"
                                                    + "Una porta segreta si è aperta a nord.");
                                            Sound.playEffect("./src/main/resources/Sounds/LockSoundEffect.wav");
                                            getCurrentRoom().getNorth().setLocked(false);
                                            Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                        } else if ((pietraFiammaPushed == false && pietraAlberoPushed == false) || (pietraFiammaPushed == false && pietraAlberoPushed == true) || (pietraFiammaPushed == true && pietraAlberoPushed == false)) {
                                            frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                            pietraFiumePushed = true;
                                        } else {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            pietraAlberoPushed = false;
                                            pietraFiumePushed = false;
                                            pietraFiammaPushed = false;
                                            isPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + ", la porta è aperta.");
                                    }
                                }
                                case 36 -> {
                                    if (!Database.loadUsedObjects(35)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        pietraFiammaPushed = true;
                                        sequence = 1;
                                        if ((pietraAlberoPushed == true && pietraFiumePushed == true) || (pietraAlberoPushed == true && pietraFiumePushed == false)) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            pietraAlberoPushed = false;
                                            pietraFiumePushed = false;
                                            pietraFiammaPushed = false;
                                            isPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + ", la porta è aperta.");
                                    }
                                }
                                case 37 -> {
                                    if (!Database.loadUsedObjects(35)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        sequence = 3;
                                        isPushed = true;
                                        if ((pietraAlberoPushed == true && pietraFiumePushed == true && isPushed == true) || (pietraAlberoPushed == true && pietraFiammaPushed && isPushed == true) || (pietraFiumePushed == true && pietraFiammaPushed == true && isPushed == true)) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            pietraAlberoPushed = false;
                                            pietraFiumePushed = false;
                                            pietraFiammaPushed = false;
                                            isPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + ", la porta è aperta.");
                                    }
                                }
                                case 38 -> {
                                    if (!Database.loadUsedObjects(35)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        sequence = 3;
                                        isPushed = true;
                                        if ((pietraAlberoPushed == true && pietraFiumePushed == true && isPushed == true) || (pietraAlberoPushed == true && pietraFiammaPushed && isPushed == true) || (pietraFiumePushed == true && pietraFiammaPushed == true && isPushed == true)) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            pietraAlberoPushed = false;
                                            pietraFiumePushed = false;
                                            pietraFiammaPushed = false;
                                            isPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + ", la porta è aperta.");
                                    }
                                }
                                case 39 -> {
                                    if (!Database.loadUsedObjects(35)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        sequence = 3;
                                        isPushed = true;
                                        if ((pietraAlberoPushed == true && pietraFiumePushed == true && isPushed == true) || (pietraAlberoPushed == true && pietraFiammaPushed && isPushed == true) || (pietraFiumePushed == true && pietraFiammaPushed == true && isPushed == true)) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            pietraAlberoPushed = false;
                                            pietraFiumePushed = false;
                                            pietraFiammaPushed = false;
                                            isPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + ", la porta è aperta.");
                                    }
                                }
                                case 20 -> {
                                    if (!Database.loadUsedObjects(20)) {
                                        if (sarcofagoLibroPushed == true && sarcofagoCerchioPushed == true && number == 2) {
                                            frame.NarrationTextAreaSetText("Sento degli ingranaggi!\n"
                                                    + "Il suono proviene dalle scale che ho visto all'inizio...");
                                            Sound.playEffect("./src/main/resources/Sounds/StairsGearSound.wav");
                                            getCurrentRoom().getWest().getWest().getWest().getWest().setLocked(false);
                                            Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                        } else if ((sarcofagoLibroPushed == false && sarcofagoCerchioPushed == false) || (sarcofagoLibroPushed == true && sarcofagoCerchioPushed == false)) {
                                            frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                            sarcofagoCoronaPushed = true;
                                        } else {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            sarcofagoLibroPushed = false;
                                            sarcofagoCerchioPushed = false;
                                            sarcofagoCoronaPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + "dovresti controllare le scale ad ovest...");
                                    }
                                }
                                case 21 -> {
                                    if (!Database.loadUsedObjects(20)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        sarcofagoLibroPushed = true;
                                        number = 1;
                                        if ((sarcofagoCerchioPushed == true && sarcofagoCoronaPushed == true)) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            sarcofagoCerchioPushed = false;
                                            sarcofagoCoronaPushed = false;
                                            sarcofagoLibroPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + "dovresti controllare le scale ad ovest...");
                                    }
                                }
                                case 24 -> {
                                    if (!Database.loadUsedObjects(20)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                        sarcofagoCerchioPushed = true;
                                        if (sarcofagoLibroPushed == true && number == 1) {
                                            number = 2;
                                        }
                                        if (sarcofagoLibroPushed == true && sarcofagoCoronaPushed == true) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            sarcofagoLibroPushed = false;
                                            sarcofagoCoronaPushed = false;
                                            sarcofagoCerchioPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + "dovresti controllare le scale ad ovest...");
                                    }
                                }
                                case 22 -> {
                                    if (!Database.loadUsedObjects(20)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                        number = 3;
                                        if ((sarcofagoLibroPushed == true && sarcofagoCerchioPushed == true) || (sarcofagoLibroPushed == true && sarcofagoCoronaPushed == true) || (sarcofagoCerchioPushed == true && sarcofagoCoronaPushed == true)) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            sarcofagoLibroPushed = false;
                                            sarcofagoCerchioPushed = false;
                                            sarcofagoCoronaPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + "dovresti controllare le scale ad ovest...");
                                    }
                                }
                                case 23 -> {
                                    frame.NarrationTextAreaSetText("Non riesco a premerlo, credo sia rotto.");
                                }
                                case 25 -> {
                                    if (!Database.loadUsedObjects(20)) {
                                        frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                        Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                        number = 3;
                                        if ((sarcofagoLibroPushed == true && sarcofagoCerchioPushed == true) || (sarcofagoLibroPushed == true && sarcofagoCoronaPushed == true) || (sarcofagoCerchioPushed == true && sarcofagoCoronaPushed == true)) {
                                            frame.NarrationTextAreaSetText("Non succede nulla.");
                                            sarcofagoLibroPushed = false;
                                            sarcofagoCerchioPushed = false;
                                            sarcofagoCoronaPushed = false;
                                        }
                                    } else {
                                        frame.NarrationTextAreaSetText("Non puoi più premere " + p.getObject().getName() + "dovresti controllare le scale ad ovest...");
                                    }
                                }
                                case 51 -> {
                                    frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                    Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                    frame.ViewAreaSetViewPath("/Img/view/GameOverSerpenti.png");
                                    Sound.playEffect("./src/main/resources/Sounds/SnakeSound.wav");

                                    EngineGUI.hideLabels();
                                }
                                case 52 -> {
                                    frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".\n"
                                            + "Il sarcofago sembra essersi aperto!");
                                    Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);

                                }
                                case 53 -> {
                                    frame.NarrationTextAreaSetText("Hai premuto " + p.getObject().getName() + ".");
                                    Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                    frame.ViewAreaSetViewPath("/Img/view/GameOverLava.png");
                                    Sound.playEffect("./src/main/resources/Sounds/LavaSound.wav");

                                    EngineGUI.hideLabels();
                                }
                            }
                        } else {
                            frame.NarrationTextAreaSetText("Non puoi interagire con quest'oggetto.");
                        }
                    } else {
                        frame.NarrationTextAreaSetText("Non riesco a trovare quest'oggetto.");
                    }
                }
                case OPEN -> {
                    AdvObject object = p.getObject();

                    if (object == null) {
                        frame.NarrationTextAreaSetText("Non riesco a trovare quest'oggetto.");
                    }
                    if (object.isOpenable()) {
                    } else {
                        frame.NarrationTextAreaSetText("Quest'oggetto non può essere aperto.");
                    }

                    switch (p.getObject().getId()) {
                        case 40 -> {
                            if (Database.loadUsedObjects(27)) {
                                if (object instanceof AdvObjectContainer container) {
                                    if (!container.getList().isEmpty()) {
                                        frame.NarrationTextAreaSetText("Hai aperto: " + object.getName() + ".\n\n"
                                                + "Al suo interno hai trovato: ");
                                        Sound.playEffect("./src/main/resources/Sounds/LockSoundEffect.wav");
                                        for (AdvObject nextItem : container.getList()) {
                                            if (!nextItem.isTaken()) {
                                                frame.NarrationTextAreaAppendText("\n- " + nextItem.getName());
                                                if (!getCurrentRoom().getObjects().contains(nextItem)) {
                                                    getCurrentRoom().getObjects().add(nextItem);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                frame.NarrationTextAreaSetText("Hai bisogno di una chiave per aprire lo scrigno.");
                            }
                        }
                        case 25 -> {
                            if (object.isOpen()) {
                                if (object instanceof AdvObjectContainer container) {
                                    if (!container.getList().isEmpty()) {
                                        if (Database.loadTakenObjects(68)) {
                                            frame.NarrationTextAreaSetText("Hai aperto: " + object.getName() + ".\n\n"
                                                    + "Hai già raccolto il suo contenuto. ");
                                        } else {
                                            frame.NarrationTextAreaSetText("Hai aperto: " + object.getName() + ".\n\n"
                                                    + "Al suo interno hai trovato: ");
                                            Sound.playEffect("./src/main/resources/Sounds/LockSoundEffect.wav");
                                            for (AdvObject nextItem : container.getList()) {
                                                if (!nextItem.isTaken()) {
                                                    frame.NarrationTextAreaAppendText("\n- " + nextItem.getName());
                                                    if (!getCurrentRoom().getObjects().contains(nextItem)) {
                                                        getCurrentRoom().getObjects().add(nextItem);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        case 50 -> {
                            if (Database.loadUsedObjects(52)) {
                                p.getObject().setOpen(true);
                                Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                                if (object instanceof AdvObjectContainer container) {
                                    if (!container.getList().isEmpty()) {
                                        frame.NarrationTextAreaSetText("Hai aperto: " + object.getName() + ".\n\n"
                                                + "Al suo interno hai trovato: ");
                                        Sound.playEffect("./src/main/resources/Sounds/BrickSliding.wav");
                                        for (AdvObject nextItem : container.getList()) {
                                            if (!nextItem.isTaken()) {
                                                frame.NarrationTextAreaAppendText("\n- " + nextItem.getName());
                                                if (!getCurrentRoom().getObjects().contains(nextItem)) {
                                                    getCurrentRoom().getObjects().add(nextItem);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                frame.NarrationTextAreaSetText("Quest'oggetto non può essere aperto.");
                            }
                        }

                    }
                }
                case LOOK -> {
                    if (getCurrentRoom().getLook() != null) {
                        frame.NarrationTextAreaSetText(getCurrentRoom().getLook());
                    } else {
                        frame.NarrationTextAreaSetText("Non riesco a trovare questa stanza.");
                    }
                }
                case EXAMINE -> {
                    if (p.getObject() != null) {
                        if ((p.getObject().getDescription() != null && p.getObject().isLookable() == true)) {
                            frame.NarrationTextAreaSetText(p.getObject().getDescription());
                            switch (p.getObject().getId()) {
                                case 18 -> {
                                    frame.ViewAreaSetViewPath("/Img/view/EsaminaIncisione.png");
                                    incisioneFuturoExaminated = true;
                                }
                                case 41 -> {
                                    frame.ViewAreaSetViewPath("/Img/view/IncisionePassato.png");
                                    incisionePassatoExaminated = true;
                                }
                            }
                        } else if (p.getObject().getId() == 3 && p.getObject().isLookable() == false) {
                            frame.NarrationTextAreaSetText("Dovresti prima raccogliere la chiave, non trovi?");
                        } else {
                            frame.NarrationTextAreaSetText("Non c'è niente da esaminare.");
                        }
                    } else {
                        frame.NarrationTextAreaSetText("Non riesco a trovare quest'oggetto.");
                    }
                }
                case PICK_UP -> {
                    AdvObject object = p.getObject();

                    if (object == null) {
                        frame.NarrationTextAreaSetText("Non riesco a trovare quest'oggetto.");
                        return;
                    }

                    if (object.isPickupable()) {
                        if (!getInventory().contains(object) && object.isTaken() == false) {
                            getInventory().add(object);
                            Database.saveObject(object.getId(), object.getName(), false);
                            frame.NarrationTextAreaSetText("Hai raccolto: " + object.getName() + "\n");
                            frame.InventoryTextAreaAppendText("\n- " + object.getName());
                            object.setTaken(true);
                            if (object.getId() == 1 && getCurrentRoom().getId() == 1) {
                                frame.ViewAreaSetViewPath("/Img/view/PrendiTorcia.png");
                                torchtaken = true;
                            }
                            if (mattonePushed == true && object.getId() == 1 && getCurrentRoom().getId() == 1) {
                                frame.ViewAreaSetViewPath("/Img/view/PrendiTorciaSpostaMattone.png");
                                torchtaken = true;
                            }
                            if (mattonePushed == true && keytaken == true && getCurrentRoom().getId() == 1) {
                                if (Database.loadTakenObjects(1)) {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiTorciaPrendiChiave.png");
                                } else {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiChiave.png");
                                }
                            }
                            if (object.getId() == 9 && getCurrentRoom().getId() == 6) {
                                if (!Database.loadTakenObjects(8)) {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiSimbolo.png");
                                } else {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiSimboloPrendiBiglietto.png");
                                }
                                symboltaken = true;
                            }
                            if (object.getId() == 8 && getCurrentRoom().getId() == 6) {
                                if (!Database.loadTakenObjects(9)) {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiBiglietto.png");
                                } else {
                                    frame.ViewAreaSetViewPath("/Img/view/PrendiSimboloPrendiBiglietto.png");
                                }
                                papertaken = true;
                            }
                            if (symboltaken == true && papertaken == true && getCurrentRoom().getId() == 6) {
                                frame.ViewAreaSetViewPath("/Img/view/PrendiSimboloPrendiBiglietto.png");
                            }
                            if (object.getId() == 17 && getCurrentRoom().getId() == 5) {
                                frame.ViewAreaSetViewPath("/Img/view/SalaIncisionePrendiTorcia.png");
                            }
                            if (object.getId() == 9 && getCurrentRoom().getId() == 6) {
                                getCurrentRoom().getEast().getObjects().add(object);
                            } else if (object.getId() == 27 && getCurrentRoom().getId() == 8) {
                                getCurrentRoom().getNorth().getObjects().add(object);
                            }
                        } else {
                            frame.NarrationTextAreaSetText("Hai già raccolto quest'oggetto.");
                        }
                    } else if (object.getId() == 3 && !Database.loadTakenObjects(3) && getCurrentRoom().getId() == 1 && mattonePushed == true) {
                        if (torchtaken == true) {
                            object.setHidden(false);
                            object.setPickupable(true);
                            object.setLookable(true);
                            object.setTaken(true);
                            getInventory().add(object);
                            frame.NarrationTextAreaSetText("Hai raccolto: " + object.getName() + "\n");
                            Database.saveObject(object.getId(), object.getName(), false);
                            frame.InventoryTextAreaAppendText("\n- " + object.getName());
                            frame.ViewAreaSetViewPath("/Img/view/PrendiTorciaPrendiChiave.png");

                            keytaken = true;
                        } else {
                            object.setHidden(false);
                            object.setPickupable(true);
                            object.setLookable(true);
                            object.setTaken(true);
                            getInventory().add(object);
                            frame.NarrationTextAreaSetText("Hai raccolto " + object.getName() + ".");
                            Database.saveObject(object.getId(), object.getName(), false);
                            frame.InventoryTextAreaAppendText("\n- " + object.getName());
                            frame.ViewAreaSetViewPath("/Img/view/PrendiChiave.png");
                            keytaken = true;
                        }
                    } else if (!Database.loadTakenObjects(7) && object.getId() == 7 && object.isHidden() == true && getCurrentRoom().getId() == 2 && statuAtrioPushed == true) {
                        object.setHidden(false);
                        object.setLookable(true);
                        object.setTaken(true);
                        getInventory().add(object);
                        frame.NarrationTextAreaSetText("Hai raccolto " + object.getName() + ".");
                        Database.saveObject(object.getId(), object.getName(), false);
                        frame.InventoryTextAreaAppendText("\n- " + object.getName());
                        frame.ViewAreaSetViewPath("/Img/view/SpostaStatuaPrendiSimbolo.png");
                    } else if (object.getId() == 55 && object.isHidden() == true && Database.loadUsedObjects(50)) {
                        object.setHidden(false);
                        object.setPickupable(true);
                        getInventory().add(object);

                        Database.saveObject(object.getId(), object.getName(), false);
                        frame.NarrationTextAreaSetText("Hai raccolto: " + object.getName() + ".\n"
                                + "La porta delle scale si è aperta!");
                        frame.InventoryTextAreaAppendText("\n- " + object.getName());
                    } else if (object.getId() == 68 && object.isHidden() == true) {
                        object.setHidden(false);
                        object.setPickupable(true);
                        object.setLookable(true);
                        getInventory().add(object);

                        Database.saveObject(object.getId(), object.getName(), false);
                        frame.NarrationTextAreaSetText("Hai raccolto: " + object.getName() + ".\n");
                        frame.InventoryTextAreaAppendText("\n- " + object.getName());
                    } else {
                        if (Database.loadTakenObjects(object.getId())) {
                            frame.NarrationTextAreaSetText("Hai già raccolto questo oggetto.");
                        } else {
                            frame.NarrationTextAreaSetText("Non riesco a trovare questo oggetto.");
                        }
                    }
                }
                case READ -> {
                    if (p.getObject() != null) {
                        if (p.getObject().isReadable()) {
                            if (getCurrentRoom().getId() == 6) {
                                frame.NarrationTextAreaSetText(p.getObject().getText());
                            } else if (getInventory().contains(p.getObject())) {
                                frame.NarrationTextAreaSetText("E' il biglietto ritrovato nel corridoio ad Ovest della sala Atrio...\n " + "Recita: 'Trova due simboli primordiali disposti in quest'area\n per avanzare nel tuo cammino..."
                                        + "usali uno per volta, ma nell'ordine corretto...'");
                            } else {
                                frame.NarrationTextAreaSetText("Non vedo nessun biglietto");
                            }
                            if (getCurrentRoom().getId() == 6 && p.getObject().getId() == 8 && !getInventory().contains(p.getObject())) {
                                frame.NarrationTextAreaSetText("Che fai leggi senza neanche raccogliere il biglietto?");
                            }
                            if (p.getObject().getId() == 54) {
                                frame.NarrationTextAreaSetText(p.getObject().getText());
                            }
                            if (p.getObject().getId() == 64) {
                                frame.NarrationTextAreaSetText(p.getObject().getText());
                            }
                            if (p.getObject().getId() == 67) {
                                frame.NarrationTextAreaSetText(p.getObject().getText());
                            }
                            if (p.getObject().getId() == 19) {
                                frame.NarrationTextAreaSetText(p.getObject().getText());
                            }
                            if (p.getObject().getId() == 42) {
                                frame.NarrationTextAreaSetText(p.getObject().getText());
                            }
                        } else {
                            frame.NarrationTextAreaSetText("Quest'oggetto non può essere letto.");
                        }
                    } else if (Database.loadTakenObjects(8)) {
                        frame.NarrationTextAreaSetText("E' il biglietto ritrovato nel corridoio ad Ovest della sala Atrio...\n " + "Non credo sia utile in questo momento.");
                    } else {
                        frame.NarrationTextAreaSetText("Non riesco a trovare quest'oggetto.");
                    }
                }
                default -> {
                    frame.NarrationTextAreaSetText("Non riesco ad eseguire questo comando.");
                }
            }
        }
    }
}
