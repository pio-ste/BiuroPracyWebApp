var input, miejscePracy, kUzyskPrzychodu, ubezpieczenieChorobowe;
function setValues() {
    input = Number(document.getElementById("input").value);
    miejscePracy = Number(document.querySelector('input[name="miejscePracy"]:checked').value);
    kUzyskPrzychodu = Number(document.querySelector('input[name="kUzyskPrzychodu"]:checked').value);
    ubezpieczenieChorobowe = Number(document.querySelector('input[name="ubezpieczenieChorobowe"]:checked').value);
}
function uPraceFromBrutto() {
    setValues();
    var UOP, sEmerytalna, sRentowa, sChorobowa,
        sumaSkladek, sZdrowotnaCala, sZdrowotnaOdliczona,
        podstOpodatkowania, podatekNalezny,
        podatekPomniejszony, zaliczkaNaPodatek, kwotaPoSkladkach, wynagrodzenie;
    var ubWypadkowe, ubEmerytalne, ubRentowe, funduszPracy, FGSP, kosztPracodawcy;
    //Wyliczanie kwoty netto
    sEmerytalna = input*0.0976;
    sRentowa = input*0.015;
    sChorobowa = input*0.0245;
    sumaSkladek = sEmerytalna + sRentowa + sChorobowa;
    kwotaPoSkladkach = input - sumaSkladek;
    sZdrowotnaCala = kwotaPoSkladkach*0.09;
    sZdrowotnaOdliczona = kwotaPoSkladkach*0.0775;
    podstOpodatkowania = Math.round(input - sumaSkladek - miejscePracy);
    podatekNalezny = podstOpodatkowania*0.17;
    podatekPomniejszony = podatekNalezny - 43.16;
    zaliczkaNaPodatek = Math.round(podatekPomniejszony-sZdrowotnaOdliczona);
    UOP = input - sumaSkladek - sZdrowotnaCala - zaliczkaNaPodatek;
    wynagrodzenie = UOP + sumaSkladek + sZdrowotnaCala + zaliczkaNaPodatek;
    //Koszt pracodawcy
    ubWypadkowe = input*0.0167;
    ubEmerytalne = input*0.0976;
    ubRentowe = input*0.065;
    funduszPracy = input*0.0245;
    FGSP = input*0.001;
    kosztPracodawcy = input + ubWypadkowe + ubEmerytalne + ubRentowe + funduszPracy + FGSP;
    document.getElementById("UOP").innerText= UOP.toFixed(2);
    document.getElementById("sEmerytalna").innerText= sEmerytalna.toFixed(2);
    document.getElementById("sRentowa").innerText= sRentowa.toFixed(2);
    document.getElementById("sChorobowa").innerText= sChorobowa.toFixed(2);
    document.getElementById("zaliczkaNaPodatek").innerText= zaliczkaNaPodatek.toFixed(2);
    document.getElementById("sZdrowotnaOdliczona").innerText= sZdrowotnaOdliczona.toFixed(2);
    document.getElementById("wynagrodzenie").innerText= wynagrodzenie.toFixed(2);
    document.getElementById("ubWypadkowe").innerText= ubWypadkowe.toFixed(2);
    document.getElementById("ubEmerytalne").innerText= ubEmerytalne.toFixed(2);
    document.getElementById("ubRentowe").innerText= ubRentowe.toFixed(2);
    document.getElementById("funduszPracy").innerText= funduszPracy.toFixed(2);
    document.getElementById("FGSP").innerText= FGSP.toFixed(2);
    document.getElementById("kosztPracodawcy").innerText= kosztPracodawcy.toFixed(2);
}

function uZlecenieFromBrutto() {
    setValues();
    var UZskladkaEmerytalna, UZskladkaRentowa, UZskladkaChorobowa, UZsumaSkladek, uzyskanyPrzychodPrzed, uzyskanyPrzychod,
        UZskladkaZdrowotna, UZskladkaZrowotnaDoOdliczenia, UZpodstawaOpodatkowania, UZzaliczka, UZzaliczkaNaPodatek,
        UZwynagrodzenie, UZwynagrodzenieWprowadzone;
    var UZubWypadkowe, UZubEmerytalne, UZubRentowe, UZfunduszPracy, UZFGSP, UZkosztPracodawcy;
    //Kwota netto
    UZskladkaEmerytalna = input * 0.0976;
    UZskladkaRentowa = input * 0.015;
    UZskladkaChorobowa = input * ubezpieczenieChorobowe;
    UZsumaSkladek = UZskladkaEmerytalna+UZskladkaRentowa+UZskladkaChorobowa;
    uzyskanyPrzychodPrzed = input - UZsumaSkladek;
    uzyskanyPrzychod = uzyskanyPrzychodPrzed * kUzyskPrzychodu;
    UZskladkaZrowotnaDoOdliczenia = uzyskanyPrzychodPrzed * 0.0775;
    UZpodstawaOpodatkowania = Math.round(uzyskanyPrzychodPrzed - uzyskanyPrzychod);
    UZzaliczka = UZpodstawaOpodatkowania * 0.17;
    UZzaliczkaNaPodatek = Math.round(UZzaliczka - UZskladkaZrowotnaDoOdliczenia);
    UZwynagrodzenie = input - UZsumaSkladek - UZzaliczka - UZzaliczkaNaPodatek;
    UZwynagrodzenieWprowadzone = UZwynagrodzenie + UZsumaSkladek + UZzaliczka + UZzaliczkaNaPodatek;
    //Koszty pracodawcy
    UZubWypadkowe = input*0.0167;
    UZubEmerytalne = input*0.0976;
    UZubRentowe = input*0.065;
    UZfunduszPracy = input*0.0245;
    UZFGSP = input*0.001;
    UZkosztPracodawcy = input + UZubWypadkowe + UZubEmerytalne + UZubRentowe + UZfunduszPracy + UZFGSP;

    document.getElementById("UZwynagrodzenie").innerText= UZwynagrodzenie.toFixed(2);
    document.getElementById("UZskladkaEmerytalna").innerText= UZskladkaEmerytalna.toFixed(2);
    document.getElementById("UZskladkaRentowa").innerText= UZskladkaRentowa.toFixed(2);
    document.getElementById("UZskladkaChorobowa").innerText= UZskladkaChorobowa.toFixed(2);
    document.getElementById("UZzaliczkaNaPodatek").innerText= UZzaliczkaNaPodatek.toFixed(2);
    document.getElementById("UZskladkaZrowotnaDoOdliczenia").innerText= UZskladkaZrowotnaDoOdliczenia.toFixed(2);
    document.getElementById("UZwynagrodzenieWprowadzone").innerText= UZwynagrodzenieWprowadzone.toFixed(2);

    document.getElementById("UZubWypadkowe").innerText= UZubWypadkowe.toFixed(2);
    document.getElementById("UZubEmerytalne").innerText= UZubEmerytalne.toFixed(2);
    document.getElementById("UZubRentowe").innerText= UZubRentowe.toFixed(2);
    document.getElementById("UZfunduszPracy").innerText= UZfunduszPracy.toFixed(2);
    document.getElementById("UZFGSP").innerText= UZFGSP.toFixed(2);
    document.getElementById("UZkosztPracodawcy").innerText= UZkosztPracodawcy.toFixed(2);
}
function uDzieloFromBrutto() {
    setValues();
    var UDuzyskanyPrzychodPrzed, UDuzyskanyPrzychodPo, UDzaliczkaNaPodatek, UDwynagrodzenie, UDwynagrodzenieWprowadzone;
    UDuzyskanyPrzychodPrzed = input * kUzyskPrzychodu;
    UDuzyskanyPrzychodPo = input - UDuzyskanyPrzychodPrzed;
    UDzaliczkaNaPodatek = UDuzyskanyPrzychodPo * 0.18;
    UDwynagrodzenie = input - UDzaliczkaNaPodatek;
    UDwynagrodzenieWprowadzone = UDwynagrodzenie + UDzaliczkaNaPodatek;
    document.getElementById("UDwynagrodzenie").innerText= UDwynagrodzenie.toFixed(2);
    document.getElementById("UDzaliczkaNaPodatek").innerText= UDzaliczkaNaPodatek.toFixed(2);
    document.getElementById("UDwynagrodzenieWprowadzone").innerText= UDwynagrodzenieWprowadzone.toFixed(2);
}