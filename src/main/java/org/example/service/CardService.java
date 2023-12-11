package org.example.service;

import org.example.dto.CardDTO;
import org.example.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    public boolean createCard(CardDTO card) {
        boolean result = cardRepository.createCard(card);
        if (result) {
            System.out.println("Card created successfuly 👌👌👌");
        }else {
            System.out.println("An error occurred while creating the card !!!");
        }
        return result;
    }

    public   List<CardDTO> getCardList() {
        List<CardDTO> cardList =cardRepository.getCardList();
        return cardList;
    }

    public void updateCard(CardDTO cardDTO) {
        boolean update = cardRepository.update(cardDTO);
        if (update){
            System.out.println("Cart updates successfuly \uD83D\uDC4C\uD83D\uDC4C\uD83D\uDC4C\"");
        }else {
            System.out.println("Not fount");
        }

    }

    public boolean chesk(String newnumber) {
      return cardRepository.chesk(newnumber);

    }

    public boolean updateStatus(String number) {
        boolean b = cardRepository.updateStatus(number);
        if (!b){
            System.out.println("Status update ");
        }{
            System.out.println("no update");
        }
        return b;
    }


    public void delete_card1(String number) {
        boolean b = cardRepository.deletecard1(number);
        if (b){
            System.out.println("Delete 👌");
        }else {
            System.out.println("no Delete 🥱");
        }
    }


}
