package ru.netology.moneytransferservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneytransferservice.model.transferObjact.TransferOperation;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferOperationRepository {

    private static final ConcurrentHashMap<Long, TransferOperation> repository = new ConcurrentHashMap<>();

    public void add(TransferOperation transferOperation) {
        repository.put(transferOperation.getId(), transferOperation);
    }

    public TransferOperation get(long id) {
        return repository.get(id);
    }

    public boolean isExist(long id) {
        return repository.containsKey(id);
    }
}
