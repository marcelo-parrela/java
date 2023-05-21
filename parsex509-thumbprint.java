

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.MessageDigest;    
import java.util.Base64;



class Thumbprint {

    public static String thumbprintSHA1(final String strX509) {
        try {
            final byte[] crtB64 = Base64.getDecoder().decode(strX509);
            final X509Certificate x509 = x509Parse(crtB64);
            final byte[] hash = MessageDigest.getInstance("SHA-1").digest(x509.getEncoded());
            final String strThumb = Base64.getEncoder().encodeToString(hash);
            final StringBuilder thumb = new StringBuilder();
            for (int x = 0; x < strThumb.length(); x++) {
                thumb.append( String.format("%02X ", (int) strThumb.charAt(x)) );
            }
            
            return thumb.toString();
        } catch ( Exception e ) {
            return null;
        }
    }
    
    private static X509Certificate x509Parse( final byte[] crtB64 ) throws Exception {
        try (
            final ByteArrayInputStream stream = new ByteArrayInputStream( crtB64 ) ) {
            return ( X509Certificate ) CertificateFactory
                .getInstance( "X.509" )
                .generateCertificate( stream );
        } catch ( Exception e ) {
            throw e;
        }
    }
    
}    
    
    
public class TesteThumbprint {
   static final String PEM
        = "MIIG7TCCBNWgAwIBAgIDAn4PMA0GCSqGSIb3DQEBCwUAMIGVMQswCQYDVQQGEwJC"
        + "UjETMBEGA1UECgwKSUNQLUJyYXNpbDE7MDkGA1UECwwyU2VydmljbyBGZWRlcmFs"
        + "IGRlIFByb2Nlc3NhbWVudG8gZGUgRGFkb3MgLSBTRVJQUk8xNDAyBgNVBAMMK0F1"
        + "dG9yaWRhZGUgQ2VydGlmaWNhZG9yYSBkbyBTRVJQUk8gRmluYWwgdjUwHhcNMTcw"
        + "NTAyMTc0NjM0WhcNMjAwNTAxMTc0NjM0WjCBpTELMAkGA1UEBhMCQlIxEzARBgNV"
        + "BAoMCklDUC1CcmFzaWwxGTAXBgNVBAsMEFBlc3NvYSBGaXNpY2EgQTMxETAPBgNV"
        + "BAsMCEFSU0VSUFJPMSswKQYDVQQLDCJBdXRvcmlkYWRlIENlcnRpZmljYWRvcmEg"
        + "U0VSUFJPQUNGMSYwJAYDVQQDDB1NQVJDRUxPIEFVR1VTVE8gUElSRVMgUEFSUkVM"
        + "QTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAM+DizV15JQ8jbL/Dwq/"
        + "dW3eOyK9GOAdJAbD4fMzV6ymPQxEHg6iQhi3gvz7bsQg/m3xQxFWeHXHJvYXYnZg"
        + "vjhtj5KZ1oUxRCxan+JV1mQrZFi7qaOpW34sIUqmG8XBLNAvasoEUoyjrYjU6MQi"
        + "gpx4tpfRZopu7Jxcz5LQmHby9xXQA9cHm2vXiWoLX7rVW0SykEb+fH/MWbjc4PNY"
        + "hmjq7Ik9Xg+ztqoMW+QDX2OxUjEOuwoCqjkN5zwzUduGfJT//fGfW1JJr8ohTesr"
        + "7OFAgJzl7L07Ur1iBu49cVnr1bOjAy424tosMZB65noD1aR6zSrG5N/l84upo6Ns"
        + "1qECAwEAAaOCAjIwggIuMB8GA1UdIwQYMBaAFOiTq+N3x1HoGpzuZFyPf7+qyW+Q"
        + "MFkGA1UdIARSMFAwTgYGYEwBAgMNMEQwQgYIKwYBBQUHAgEWNmh0dHA6Ly9yZXBv"
        + "c2l0b3Jpby5zZXJwcm8uZ292LmJyL2RvY3MvZHBjc2VycHJvYWNmLnBkZjCBiAYD"
        + "VR0fBIGAMH4wPKA6oDiGNmh0dHA6Ly9yZXBvc2l0b3Jpby5zZXJwcm8uZ292LmJy"
        + "L2xjci9hY3NlcnByb2FjZnY1LmNybDA+oDygOoY4aHR0cDovL2NlcnRpZmljYWRv"
        + "czIuc2VycHJvLmdvdi5ici9sY3IvYWNzZXJwcm9hY2Z2NS5jcmwwVgYIKwYBBQUH"
        + "AQEESjBIMEYGCCsGAQUFBzAChjpodHRwOi8vcmVwb3NpdG9yaW8uc2VycHJvLmdv"
        + "di5ici9jYWRlaWFzL2Fjc2VycHJvYWNmdjUucDdiMIGdBgNVHREEgZUwgZKgOAYF"
        + "YEwBAwGgLwQtMjExMjE5NzMwMDE1NTg2NjY5OTIwNDMzODg0ODQ4MDAwMDAwMDAw"
        + "MDAwMDAwoBcGBWBMAQMGoA4EDDAwMDAwMDAwMDAwMKAeBgVgTAEDBaAVBBMwMDAw"
        + "MDAwMDAwMDAwMDAwMDAwgR1tYXJjZWxvLnBhcnJlbGFAc2VycHJvLmdvdi5icjAO"
        + "BgNVHQ8BAf8EBAMCBeAwHQYDVR0lBBYwFAYIKwYBBQUHAwQGCCsGAQUFBwMCMA0G"
        + "CSqGSIb3DQEBCwUAA4ICAQBrY7bdkAFGbOvMR0bsX3lrW/diBgMu/N5JNTQF4fmE"
        + "xbVyCEMMGLyHw3l3uNDknKX052HB3J0SmBMBhBc4Y7n3K0yp4Qmcm+kNt1uMwXQ9"
        + "LhORV+2CIW8sncApeoEEBcYlNjF393d/VaD2tM5OxgY3Hrd2NSSIfUCASfFXGO51"
        + "CYWV+OGFWDVMEjSvD9ybgGknmf9etIYRCN+jjG2AgzKwpcMHULn7lzVz3Oj5Y7w/"
        + "GU30wHxMwPtRnt20tq/Qhfh9X3D9ZT0CCrroFAc6TVCGNqfmj/3ohMV3cUqfCvOv"
        + "yIaZzkNBGbZ2Tq6vCLDegIRQV6sQ6PDsH7LaeRovMtmxne+klXNCPrHVGsqBBuf8"
        + "8CI3yAxJHEcCOzSkQM+jLbks23FU//wFBfLJVPWsVgDJScklSTjlFhMRiD/nv0N3"
        + "ooipPHvJnuSWXV92ORQyUTYxN0HceySbPXAmJOLgzi3rAGmTZqzxF/Y6MLp5wrSv"
        + "sArZiX9voqqSTaNpgMFyNgiDSWOsVohUrlhHfGw4p6vgYXDxqriPZGwgjyYvFTdg"
        + "GDjkRWZa0ds1r6daq3wGddvIp1r6F3B4qYQlF+bIlHswBGqEnqegAdVMGRQUbuYQ"
        + "eBZJPpQ3RwWWq+i+A8rugkLHfP4zRVCfhsJbxRkCeTK8wfSuuJTeA6Vov4NRwp06"
        + "fQ==";

    public static void main (String[] args) {
        final String thumb = Thumbprint.thumbprintSHA1(PEM);
        System.out.println("");    
        System.out.println(thumb);
        return;
    }
    
}    
    
    
    
    
