package ma.enset.sma;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.lang.xml.XMLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
public class BuyerAgentSL extends Agent {
    private Ontology catalogOntology=CatalogOntology.getCatalogOntology();
    private SLCodec slCodec =new SLCodec();
    @Override
    protected void setup() {
        getContentManager().registerOntology(catalogOntology,CatalogOntology.ONTOLOGY_NAME);
        getContentManager().registerLanguage(slCodec);
        MessageTemplate messageTemplate=MessageTemplate.and
                (MessageTemplate.MatchOntology(CatalogOntology.ONTOLOGY_NAME),
                        MessageTemplate.MatchLanguage(slCodec.getName()));
        ACLMessage receivedMessage = blockingReceive(messageTemplate);
        try {
            Disponible disponible = (Disponible)getContentManager().extractContent(receivedMessage);
//                System.out.println(disponible);
            System.out.println(receivedMessage);
        } catch (Codec.CodecException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        }
    }
}