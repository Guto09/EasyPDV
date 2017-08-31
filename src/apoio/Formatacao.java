package apoio;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import com.toedter.calendar.JDateChooser;
import java.text.*;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import javax.swing.text.*;

public class Formatacao {

    static DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static JFormattedTextField getFormatado(String formato) {
        JFormattedTextField campoFormatado = null;
        MaskFormatter format = new MaskFormatter();

        format.setPlaceholderCharacter(' ');
        format.setValueContainsLiteralCharacters(false);

        try {
            format.setMask(formato);
            campoFormatado = new JFormattedTextField(format);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return campoFormatado;
    }

    public static void formatarDecimal(JTextField campo) {
        campo.setText(df.format(Double.parseDouble(campo.getText())));
    }

    public static JFormattedTextField getTelefone() {
        return getFormatado("(##) ####-####");
    }

    public static JFormattedTextField getCNPJ() {
        return getFormatado("##.###.###/####-##");
    }

    public static JFormattedTextField getCPF() {
        return getFormatado("###.###.###-##");
    }

    public static JFormattedTextField getData() {
        return getFormatado("##/##/####");
    }



    public void formatoDecimal(JTextField campo) {
        campo.setText(df.format(Double.parseDouble(campo.getText())));
    }

    public static void reformatarData(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##/##/####");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarCpf(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("###.###.###-##");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarCnpj(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##.###.###/####-##");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarTelefone(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("(##)####-####");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static String ajustaDataDMA(String data) {
        String dataFormatada = null;
        try {
            Date dataAMD = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataAMD);
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataFormatada);
    }
    
        public static String ajustaDataDMAHora(String data) {
        String dataFormatada = null;
        try {
            Date dataAMD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data);
            dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataAMD);
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataFormatada);
    }

    public static String ajustaDataDMAJCalendar(int dia, int mes, int ano) {
        String dataFormatada = null;
        mes = (mes+1);
        ano = (ano+1900);
        if (dia < 10) {
            dataFormatada = "0"+dia+"/";
        }else{
            dataFormatada = dia+"/";
        }
        
        if (mes < 10) {
            dataFormatada = dataFormatada+"0"+mes+"/";
        }else{
            dataFormatada = dataFormatada+mes+"/";
        }
        dataFormatada = dataFormatada+ano;
        
        return dataFormatada;
    }

    public static String ajustaDataDMAJCalendar(JDateChooser tffDataInicio) {
        String dataFormatada = "";
        
        if (tffDataInicio.getDate().getDate()<10) {
            dataFormatada = "0"+tffDataInicio.getDate().getDate()+"/";
        }else{
            dataFormatada = dataFormatada+tffDataInicio.getDate().getDate()+"/";
        }
        
        if ((tffDataInicio.getDate().getMonth()+1)<10) {
            dataFormatada = dataFormatada +"0"+(tffDataInicio.getDate().getMonth()+1)+"/";
        }else{
            dataFormatada = dataFormatada +(tffDataInicio.getDate().getMonth()+1)+"/";
        }
        dataFormatada= (dataFormatada+(tffDataInicio.getDate().getYear()+1900));
        
        return dataFormatada;
    }
    
    
    public static String ajustaDataDMAJCalendar(Date tffDataInicio) {
        String dataFormatada = "";
        
        if (tffDataInicio.getDate()<10) {
            dataFormatada = "0"+tffDataInicio.getDate()+"/";
        }else{
            dataFormatada = dataFormatada+tffDataInicio.getDate()+"/";
        }
        
        if ((tffDataInicio.getMonth()+1)<10) {
            dataFormatada = dataFormatada +"0"+(tffDataInicio.getMonth()+1)+"/";
        }else{
            dataFormatada = dataFormatada +(tffDataInicio.getMonth()+1)+"/";
        }
        dataFormatada= (dataFormatada+(tffDataInicio.getYear()+1900));
        
        return dataFormatada;
    }
    
    
    public static String ajustaDataAMD(String data) {
        String dataFormatada = null;
        try {
            Date dataDMA = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(dataDMA);
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataFormatada);
    }
    
        public static String ajustaDataAMDHora(String data) {
        String dataFormatada = null;
        try {
            Date dataDMA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data);
            dataFormatada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataDMA);
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataFormatada);
    }

    public static String removerFormatacao(String dado) {
        String retorno = "";
        for (int i = 0; i < dado.length(); i++) {
            if (dado.charAt(i) != '.' && dado.charAt(i) != '/' && dado.charAt(i) != '-') {
                retorno = retorno + dado.charAt(i);
            }
        }
        return (retorno);
    }

    public static String getDataAtual() {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataHoje = df.format(now);

        return dataHoje;
    }
}
