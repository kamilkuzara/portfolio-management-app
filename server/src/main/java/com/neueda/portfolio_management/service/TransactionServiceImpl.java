package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.dto.TransactionRequest;
import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.entity.Transaction;
import com.neueda.portfolio_management.enums.TransactionType;
import com.neueda.portfolio_management.exception.InvalidAssetException;
import com.neueda.portfolio_management.exception.NotFoundException;
import com.neueda.portfolio_management.repository.AssetRepository;
import com.neueda.portfolio_management.repository.TransactionRepository;
import com.neueda.portfolio_management.exception.InvalidTransactionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Profile("db")
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private AssetRepository assetRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AssetRepository assetRepository) {
        this.transactionRepository = transactionRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAsset(String asset){
        return transactionRepository.findAllByAssetNameIgnoreCaseOrderByDateAsc(asset);
    }

    @Override
    public Transaction createTransaction(TransactionRequest transactionRequest){
        Asset asset;
        if (transactionRequest.getAssetId() != null) {  // if id of the asset is provided, i.e. we expect the asset to be in the portfolio
            asset = assetRepository.findById(transactionRequest.getAssetId())
                    .orElseThrow(() -> new NotFoundException("Asset not found in portfolio"));

            // if we get here, asset exists in the portfolio

            TransactionType transactionType = transactionRequest.getType();

            // update the asset based on transaction type
            switch (transactionType) {
                case BUY:
                    asset.setQuantity(asset.getQuantity().doubleValue() + transactionRequest.getQuantity().doubleValue());
                    asset = assetRepository.saveAndFlush(asset);
                    break;
                case SELL:
                    double newQuantity = asset.getQuantity().doubleValue() - transactionRequest.getQuantity().doubleValue();

                    if (newQuantity < 0)
                        throw new InvalidTransactionException("Invalid transaction quantity. Cannot be more that the current quantity of the asset");

                   asset.setQuantity(newQuantity);
                   asset = assetRepository.saveAndFlush(asset);

                    break;
            }

            Transaction transaction = new Transaction();
            transaction.setAsset(asset);
            transaction.setQuantity(transactionRequest.getQuantity());
            transaction.setDate(transactionRequest.getDate());
            transaction.setType(transactionType);
            transaction.setPricePerUnit(transactionRequest.getPricePerUnit());

            return transactionRepository.saveAndFlush(transaction);

        } else {
            if(transactionRequest.getAssetName() == null || transactionRequest.getAssetType() == null)
                throw new InvalidAssetException("Invalid asset specification. Need to specify assetName and assetType");

            if(transactionRequest.getAssetName().isEmpty() || transactionRequest.getAssetType().isEmpty())
                throw new InvalidAssetException("Invalid asset specification. assetName and assetType cannot be empty");

            asset = assetRepository.findByName(transactionRequest.getAssetName()).orElse(null);

            if(asset == null) {
                asset = new Asset();
                asset.setName(transactionRequest.getAssetName());
                asset.setType(transactionRequest.getAssetType());
                asset.setQuantity(0.0);
            }

            switch (transactionRequest.getType()) {
                case BUY:
                    asset.setQuantity(asset.getQuantity() + transactionRequest.getQuantity().doubleValue());
                    break;
                case SELL:
                    double newQuantity = asset.getQuantity().doubleValue() - transactionRequest.getQuantity().doubleValue();
                    if(newQuantity < 0)
                        throw new InvalidTransactionException("Invalid transaction quantity. Cannot be more that the current quantity of the asset");

                    asset.setQuantity(newQuantity);
                    break;
            }

            assetRepository.saveAndFlush(asset);

            Transaction transaction = new Transaction();
            transaction.setAsset(asset);
            transaction.setQuantity(transactionRequest.getQuantity());
            transaction.setDate(transactionRequest.getDate());
            transaction.setType(transactionRequest.getType());
            transaction.setPricePerUnit(transactionRequest.getPricePerUnit());

            return transactionRepository.saveAndFlush(transaction);
        }
    }
}
