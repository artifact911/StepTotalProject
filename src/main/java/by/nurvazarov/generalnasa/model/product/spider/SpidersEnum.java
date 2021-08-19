package by.nurvazarov.generalnasa.model.product.spider;

public enum SpidersEnum {

    ACANTHOSCURRIA_GENICULATA(1,"Акантоскуррия Геникулята"),

    BRAHYPELMA_SMITHI(2,"Брахипелма Смитти"),

    BRAHYPELMA_EMILIA(3,"Брахипелма Эмилия"),

    BRAHYPELMA_VAGANS(4,"Брахипелма Ваганс"),

    BRAHYPELMA_ALBOPILOSUM(5,"Брахипелма Альбопилосум"),

    BRAHYPELMA_BOEHMEI(6,"Брахипелма Бэми"),

    GRAMOSTOLLA_PULCHRA(7,"Граммостолла Пульхра"),

    HETEROSCODRA_MACULATE(8,"Хетероскодра Макулята"),

    HETEROCRATES_HERCULES(9,"Хетерократес Геркулес"),

    LASIDORA_PARACHYBANA(10,"Ласидора Парахибана"),

    MONOCETROPUS_BALFOURI(11,"Моноцетропус Балфори"),

    NANDU_CHROMATUS(12,"Нанду Хроматус"),

    NANDU_COLORATOVILLOSUS(13,"Нанду Колоравилосус"),

    ORNITHOCTONUS_AUREOTIBIALIS(14,"Орнитоктонус Ауреотибалис"),

    PELENOBIUS_MUTICUS(15,"Пеленобиус Мутикус"),

    POECILOTHERIA_REGALIS(16,"Пецилотерия Регалис"),

    POECILOTHERIA_ORNATA(17,"Пецилотерия Орната"),

    POECILOTHERIA_METALLICA(18,"Пецилотерия Металлика"),

    POECILOTHERIA_FORMOSA(19,"Пецилотерия Формоза"),

    PSALMOPOEUS_IRMINIA(20,"Псалмопеус Ирминия"),

    PSALMOPOEUS_CAMBRIGEI(21,"Псалмопеус Кембридж"),

    PSALMOPOEUS_PULCHER(22,"Псалмопеус Пульчер"),

    PTERINOCHILUS_MURINUS_RCF(23,"Птеринохилус Муринус"),

    XENESTIS_IMMANIS(24,"Ксенестис Имманис"),

    THERAPHOSA_BLONDI(25,"Терафоса Блонда"),

    THERAPHOSA_STIRMI(26,"Терафоса Стирми");


    private final int index;
    private final String rusName;

    SpidersEnum(int index, String rusName) {
        this.index = index;
        this.rusName = rusName;
    }

    public int getIndex() {
        return index;
    }

    public String getRusName() {
        return rusName;
    }
}
