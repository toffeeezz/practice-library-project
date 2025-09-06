import java.util.ArrayList;

public class Library extends ArrayList<Book>{

    public Library(){

        // ===== GRIMOIRE =====
        this.add(new Book(
                "The Key of Solomon",
                "An ancient grimoire etched in fractured Latin and Hebrew, where prayers coil into chains and sigils command legions unseen. Its cracked leather binding has weathered kingdoms, and its words speak of the dominion of man over spirit, yet every invocation demands a toll of flesh or soul. The Key is no scripture of salvation but of peril: those who falter in its rites invite not angels but hungering shades. To wield it is to walk the narrowing bridge between power and ruin, where even kings have toppled into shadow for daring to bind what should never be called.",
                Genre.GRIMOIRE,
                Author.SOLOMON_THE_WISE,
                AccessLevel.MASTERAL,
                "/resources/textures/bookDisplays/key-of-solomon.jpg",
                "Key of Solomon, grimoire, forbidden knowledge, cursed book, occult, summoning, ritual magic, dark magic, ancient text, demonology, angelic seals, Hebrew magic, ritual circles, invocation, exorcism, spiritual dominion, mystical control, shadow binding"
        ));

        this.add(new Book(
                "Lesser Key of Solomon",
                "A grimoire of conjurations that claims to chain the seventy-two spirits once bound by King Solomon himself. Its seals and rites promise dominion over infernal legions, yet each invocation carves away a piece of the soul. Where the Key guided, the Lemegeton commands — and often damns.",
                Genre.GRIMOIRE,
                Author.SOLOMON_THE_WISE,
                AccessLevel.ORIGIN,
                "/resources/textures/bookDisplays/lesser-key-of-solomon.jpg",
                "Lemegeton, Lesser Key of Solomon, grimoire, forbidden knowledge, cursed book, occult, summoning, ritual magic, dark magic, ancient text, demonology, infernal seals, goetia, spirit binding, infernal dominion"
        ));

        this.add(new Book(
                "Necronomicon",
                "A tome whispered of in madness, bound not in vellum but in the silence of graves. Its pages chart the void beyond stars, where elder things stir and hunger with patient eternity. To read is to invite visions of cyclopean cities and oceans of black seas that erode the mind; to remember is to feel sanity peel away like rotted bark. No author claims it, yet all who name it know the blight of Abdul Alhazred, the mad poet whose quill bled truths best left unscribed. The Necronomicon does not merely instruct—it ensnares, unmaking the reader until they too are but another verse in its endless dirge.",
                Genre.GRIMOIRE,
                Author.ABDUL_ALHAZRED,
                AccessLevel.ORIGIN,
                "/resources/textures/bookDisplays/necronomicon.jpg",
                "Necronomicon, grimoire, forbidden knowledge, cursed book, occult, summoning, ritual magic, dark magic, ancient text, cosmic horror, madness, eldritch beings, Abdul Alhazred, necromancy, insanity, void lore, lost civilizations"
        ));

// ===== OCCULT =====
        this.add(new Book(
                "Picatrix",
                "The Picatrix, an ancient Arabic grimoire traditionally attributed to Maslama al-Majriti, whispers the secrets of the stars and the hidden laws of the cosmos. Its cracked leather pages, etched with sigils older than kingdoms long fallen, pulse with a cold, watchful intelligence. Within lies the arcane knowledge of celestial alignments and forbidden rites, precise yet cruel, offering power that fractures the mind and erodes the soul. Scholars who dare translate its cryptic diagrams risk being consumed by the void it inhabits, glimpsing the harmony of the heavens only as their own humanity slips away. It is not a book, but a perilous covenant, a shadowed path where knowledge and damnation entwine.",
                Genre.OCCULT,
                Author.MASLAMA_AL_MAJRITI,
                AccessLevel.MASTERAL,
                "/resources/textures/bookDisplays/picatrix.jpg",
                "Picatrix, occult, astral magic, talisman, forbidden knowledge, alchemy, astrology, medieval grimoire, magical rites, celestial influence, cosmic alignment, star lore, ethereal forces"
        ));

        this.add(new Book(
                "Ars Notoria",
                "The Ars Notoria, whispered to be Solomon’s own craft, is a labyrinth of sacred prayers and celestial glyphs, designed to bend the mind toward unearthly knowledge. Its pages hum with unseen power, each word a key to memory and eloquence, yet each invocation exacts a toll upon the soul. Those who linger over its diagrams may find themselves gifted beyond human limits, but haunted by visions that erode reason and tether the spirit to realms unseen. It is a tome of illumination and damnation intertwined, where mastery of thought becomes both blessing and curse.",
                Genre.GRIMOIRE,
                Author.SOLOMON_THE_WISE,
                AccessLevel.EXPERT,
                "/resources/textures/bookDisplays/ars-notoria.jpg",
                "Ars Notoria, occult, memory enhancement, intellect, prayers, mystical invocations, spiritual peril, medieval magic, mental mastery, divine wisdom, arcane instruction"
        ));

        this.add(new Book(
                "Heptameron",
        "The Heptameron is a ritual grimoire of angelic summoning, its pages filled with solemn diagrams and incantations that pulse with otherworldly authority. Each day’s rite calls forth a host of celestial beings, their obedience absolute, their gaze cold and indifferent. The practitioner becomes both instrument and witness, caught between devotion and dread, as the text grants power not through force but through exacting precision. Too eager or careless, and the angels’ silent judgment gnaws at the soul, leaving brilliance tinged with unease.",
                Genre.OCCULT,
                Author.PETER_OF_ABANO,
                AccessLevel.EXPERT,
                "/resources/textures/bookDisplays/heptameron.jpg",
                "Heptameron, occult, ritual magic, summoning, angels, spirits, forbidden knowledge, medieval grimoire, spiritual binding, ceremonial rites, mystical hierarchy"
        ));

        this.add(new Book(
                "Book of Enoch",
        "The Book of Enoch is a relic of a world long swallowed by time, its brittle pages murmuring of Watchers who fell from the heavens and scattered corruption upon the earth. Each line is a shard of forgotten prophecy, a vision of cataclysms that claw at the mind and gnaw at the soul. To read it is to walk among shadows older than kingdoms, to feel the weight of divine judgment pressing like stone upon the chest. Knowledge here does not enlighten—it exacts, leaving those who dare its truths hollowed, yet haunted by the echoes of heaven’s rebellion.",
                Genre.PROPHECY,
                Author.ENOCH,
                AccessLevel.ORIGIN,
                "/resources/textures/bookDisplays/book-of-enoch.jpg",
                "Book of Enoch, apocalyptic, prophecy, forbidden knowledge, angels, fallen angels, divine wrath, celestial secrets, visions, end times, cosmic order, heavenly judgment"
        ));

        this.add(new Book(
                "Prophecies of Nostradamus",
        "The Prophecies of Nostradamus are a tapestry of shadows, each quatrain a riddle that whispers of doom yet to come. Its pages seem to shimmer with a cold inevitability, as if the ink itself foretells the fall of kings, the rise of plague, and the slow unraveling of the world. Those who pore over it feel the weight of fate pressing upon their shoulders, glimpsing futures that twist hope into despair and ambition into ashes. This is no guide to mastery, but a mirror to mortality, showing all who read the fragility of men beneath the indifferent gaze of time.",
                Genre.PROPHECY,
                Author.NOSTRADAMUS,
                AccessLevel.NOVICE,
                "/resources/textures/bookDisplays/prophecies-of-nostradamus.jpg",
                "Nostradamus, prophecy, apocalyptic, quatrains, disasters, wars, future knowledge, end times, prediction, occult, temporal foretelling, cryptic imagery"
        ));

        this.add(new Book(
                "Apocalypse of Baruch",
        "The Apocalypse of Baruch unfolds as a chronicle of despair, where every vision is a hammer striking the bones of kingdoms long fallen. Fire devours cities, rivers run black with sorrow, and the air itself seems heavy with the inevitability of decay. Those who read its lines find themselves trapped between witness and victim, feeling the slow, relentless erosion of hope. The text does not offer guidance or comfort; it lays bare the futility of all striving, a testament to ruin that waits patiently for all who breathe.",
                Genre.PROPHECY,
                Author.BARUCH,
                AccessLevel.NOVICE,
                "/resources/textures/bookDisplays/apocalypse-of-baruch.jpg",
                "Apocalypse of Baruch, prophecy, apocalyptic, divine judgment, end times, future knowledge, visions, celestial order, historical text, prophetic calamities, moral reckoning"
        ));


    }


}
